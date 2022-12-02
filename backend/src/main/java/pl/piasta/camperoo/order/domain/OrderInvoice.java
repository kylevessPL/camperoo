package pl.piasta.camperoo.order.domain;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.settings.HorizontalAlignment;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.TextCell;
import pl.piasta.camperoo.product.domain.Product;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.awt.Color.LIGHT_GRAY;
import static java.awt.Color.WHITE;
import static java.util.Objects.nonNull;
import static org.vandeseer.easytable.settings.HorizontalAlignment.CENTER;
import static org.vandeseer.easytable.settings.HorizontalAlignment.LEFT;
import static org.vandeseer.easytable.settings.HorizontalAlignment.RIGHT;
import static org.vandeseer.easytable.settings.VerticalAlignment.TOP;

class OrderInvoice implements Closeable {
    public static final int PAGE_HEADING_FONT_SIZE = 16;
    public static final int SECTION_HEADING_FONT_SIZE = 12;
    public static final int TEXT_FONT_SIZE = 10;
    public static final int SUMMARY_TABLE_CELL_FONT_SIZE = 9;
    public static final int SUMMARY_TABLE_HEADER_FONT_SIZE = 11;
    private static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneId.systemDefault());
    private static final float PADDING = 50f;
    private static final Color GREEN = new Color(152, 238, 138);
    private static final Color BLUE_DARK = new Color(76, 129, 190);
    private static final Color BLUE_LIGHT_1 = new Color(186, 206, 230);
    private static final Color BLUE_LIGHT_2 = new Color(218, 230, 242);
    private final Order order;
    private final PDDocument invoice;
    private final Map<FontType, PDFont> fonts;

    OrderInvoice(@NonNull Order order) throws IOException {
        this.order = order;
        this.invoice = new PDDocument();
        this.fonts = loadFonts();
        createInvoice();
    }

    public byte[] getRawData() throws IOException {
        try (var output = new ByteArrayOutputStream()) {
            invoice.save(output);
            return output.toByteArray();
        }
    }

    @Override
    public void close() throws IOException {
        invoice.close();
    }

    protected Map<FontType, PDFont> loadFonts() throws IOException {
        Map<FontType, PDFont> map = new EnumMap<>(FontType.class);
        for (var type : FontType.values()) {
            map.put(type, loadFont(invoice, type.actualName));
        }
        return map;
    }

    protected void createInvoice() throws IOException {
        var page = createPage();
        invoice.addPage(page);
        try (var contentStream = new PDPageContentStream(invoice, page)) {
            drawHeading(page, contentStream);
            drawInvoiceNumberSection(page, contentStream);
            drawIssuanceDateSection(page, contentStream);
            drawOrderDateSection(page, contentStream);
            drawPaymentTypeSection(page, contentStream);
            drawTableSection(page, contentStream);
        }
    }

    private void drawTableSection(PDPage page, PDPageContentStream content) throws IOException {
        var drawer1 = createSellerBuyerTableDrawer(page, content);
        drawTable(invoice, drawer1);
        var drawer2 = createSummaryTableDrawer(page, content, drawer1.getFinalY());
        drawTable(invoice, drawer2);
    }

    private void drawHeading(PDPage page, PDPageContentStream content) throws IOException {
        var text = "Invoice";
        var fontSize = PAGE_HEADING_FONT_SIZE;
        var offsetX =
                (getPageWidth(page) - calculateTextWidth(text, fonts.get(FontType.OPEN_SANS), fontSize)) / 2;
        var offsetY = getPageHeight(page) - PADDING;
        drawTextBold(content, text, fontSize, offsetX, offsetY);
    }

    private void drawInvoiceNumberSection(PDPage page, PDPageContentStream content) throws IOException {
        var text = "Order No.: " + order.getId();
        drawText(content, text, TEXT_FONT_SIZE, PADDING, getPageHeight(page) - PADDING - 30);
    }

    private void drawIssuanceDateSection(PDPage page, PDPageContentStream content) throws IOException {
        var text = "Date of issue: " + dateTimeFormatter.format(Instant.now());
        drawText(content, text, TEXT_FONT_SIZE, PADDING, getPageHeight(page) - PADDING - 50);
    }

    private void drawOrderDateSection(PDPage page, PDPageContentStream content) throws IOException {
        var text = "Date of order: " + dateTimeFormatter.format(order.getPlacementDate());
        drawText(content, text, TEXT_FONT_SIZE, PADDING, getPageHeight(page) - PADDING - 70);
    }

    private void drawPaymentTypeSection(PDPage page, PDPageContentStream content) throws IOException {
        var payment = order.getPayment();
        if (payment.isPresent()) {
            var text = "Payment form: " + payment.get().getType().getLocalizedName(Locale.ENGLISH);
            drawText(content, text, TEXT_FONT_SIZE, PADDING, getPageHeight(page) - PADDING - 90);
        }
    }

    private void drawText(PDPageContentStream content, String text, int fontSize, float offsetX, float offsetY
    ) throws IOException {
        drawText(content, text, fonts.get(FontType.OPEN_SANS), fontSize, offsetX, offsetY);
    }

    private void drawTextBold(PDPageContentStream content, String text, int fontSize, float offsetX, float offsetY
    ) throws IOException {
        drawText(content, text, fonts.get(FontType.OPEN_SANS_BOLD), fontSize, offsetX, offsetY);
    }

    private TableDrawer createSummaryTableDrawer(PDPage page, PDPageContentStream content, float offsetY) {
        return TableDrawer.builder()
                .page(page)
                .contentStream(content)
                .table(createSummaryTable(page))
                .startX(PADDING)
                .startY(offsetY - 20)
                .endY(PADDING)
                .build();
    }

    private TableDrawer createSellerBuyerTableDrawer(PDPage page, PDPageContentStream content) {
        return TableDrawer.builder()
                .page(page)
                .contentStream(content)
                .table(createSellerBuyerTable(page))
                .startX(PADDING)
                .startY(getPageHeight(page) - PADDING - 90)
                .endY(PADDING)
                .build();
    }

    private Table createSellerBuyerTable(PDPage page) {
        var genericColumnWidth = getTableMaxWidth(page) / 2;
        return Table.builder()
                .addColumnsOfWidth(genericColumnWidth, genericColumnWidth)
                .fontSize(10)
                .font(fonts.get(FontType.OPEN_SANS))
                .borderColor(WHITE)
                .rows(createSellerBuyerTableRows())
                .build();
    }

    private Table createSummaryTable(PDPage page) {
        var genericColumnWidth = getTableMaxWidth(page) / 4;
        var productColumnWidth = genericColumnWidth * 2;
        return Table.builder()
                .addColumnsOfWidth(productColumnWidth, genericColumnWidth, genericColumnWidth)
                .fontSize(10)
                .font(fonts.get(FontType.OPEN_SANS))
                .borderColor(WHITE)
                .rows(createSummaryTableRows())
                .build();
    }

    private List<Row> createSummaryTableRows() {
        return Stream.of(
                List.of(createSummaryHeaderRow()),
                createSummaryProductRows(),
                List.of(createSummaryPriceRow(PriceType.SUBTOTAL)),
                List.of(createDiscountRow()),
                List.of(createSummaryPriceRow(PriceType.TOTAL))
        ).flatMap(List::stream).toList();
    }

    private List<Row> createSellerBuyerTableRows() {
        return Stream.of(
                List.of(createSellerBuyerHeaderRow()),
                List.of(createSellerBuyerDetailsRow())
        ).flatMap(List::stream).toList();
    }

    private Row createSellerBuyerHeaderRow() {
        return Row.builder()
                .add(createCell("Seller", LEFT))
                .add(createCell("Buyer", LEFT))
                .font(fonts.get(FontType.OPEN_SANS_BOLD))
                .fontSize(SECTION_HEADING_FONT_SIZE)
                .build();
    }

    private Row createSellerBuyerDetailsRow() {
        var sellerDetails = createSellerDetails();
        var buyerDetails = createBuyerDetails();
        return Row.builder()
                .add(createCell(sellerDetails, LEFT))
                .add(createCell(buyerDetails, LEFT))
                .font(fonts.get(FontType.OPEN_SANS))
                .fontSize(TEXT_FONT_SIZE)
                .build();
    }

    private String createSellerDetails() {
        var branch = order.getCompanyBranch();
        StringBuilder builder = new StringBuilder();
        builder.append(branch.getName());
        builder.append(System.lineSeparator()).append(processAddress(branch.getAddress()));
        if (nonNull(branch.getPhoneNumber())) {
            builder.append(System.lineSeparator()).append("Phone: ").append(branch.getPhoneNumber());
        }
        builder.append(System.lineSeparator()).append("Email: ").append(branch.getEmail().getEmail());
        return builder.toString();
    }

    private String createBuyerDetails() {
        var user = order.getUser();
        var person = user.getPerson();
        StringBuilder builder = new StringBuilder();
        builder.append(person.getFirstName()).append(" ").append(person.getLastName());
        builder.append(System.lineSeparator()).append(person.getAddressOne());
        if (nonNull(person.getAddressTwo())) {
            builder.append(System.lineSeparator()).append(person.getAddressTwo());
        }
        builder.append(System.lineSeparator()).append(person.getZipCode()).append(" ").append(person.getCity());
        builder.append(System.lineSeparator()).append("Phone: ").append(person.getPhoneNumber());
        builder.append(System.lineSeparator()).append("Email: ").append(user.getEmailAddress().getEmail());
        return builder.toString();
    }

    private Row createSummaryHeaderRow() {
        return Row.builder()
                .add(createCell("Product", LEFT))
                .add(createCell("Quantity", CENTER))
                .add(createCell("Total", CENTER))
                .backgroundColor(BLUE_DARK)
                .textColor(WHITE)
                .font(fonts.get(FontType.OPEN_SANS_BOLD))
                .fontSize(SUMMARY_TABLE_HEADER_FONT_SIZE)
                .borderWidth(1)
                .build();
    }

    private List<Row> createSummaryProductRows() {
        IntFunction<Color> backgroundColor = index -> index % 2 == 0 ? BLUE_LIGHT_1 : BLUE_LIGHT_2;
        var items = order.getOrderProducts().toArray(OrderProduct[]::new);
        return IntStream
                .range(0, items.length)
                .mapToObj(i -> createProductRow(items[i], backgroundColor.apply(i)))
                .toList();
    }

    private Row createProductRow(OrderProduct orderProduct, Color backgroundColor) {
        Product product = orderProduct.getProduct();
        return Row.builder()
                .add(createCell(product.getLocalizedName(Locale.ENGLISH).getName(), LEFT))
                .add(createCell(orderProduct.getQuantity().toString(), RIGHT))
                .add(createCell(orderProduct.getTotalPrice().toString(), RIGHT))
                .backgroundColor(backgroundColor)
                .borderWidth(1)
                .build();
    }

    private Row createDiscountRow() {
        var discount = order.getDiscount().getValue();
        var saving = order.getTotalPrice().subtract(order.getSubtotalPrice());
        return Row.builder()
                .add(createSummaryCellName("Discount – %d%%".formatted(discount), GREEN))
                .add(createSummaryCellValue(saving + " PLN"))
                .borderWidth(1)
                .horizontalAlignment(RIGHT)
                .build();
    }

    private Row createSummaryPriceRow(PriceType priceType) {
        return Row.builder()
                .add(createSummaryCellName(priceType.label, BLUE_DARK))
                .add(createSummaryCellValue(order.getSubtotalPrice() + " PLN"))
                .borderWidth(1)
                .horizontalAlignment(RIGHT)
                .build();
    }

    private TextCell createCell(String name, HorizontalAlignment horizontalAlignment) {
        return TextCell.builder()
                .text(name)
                .horizontalAlignment(horizontalAlignment)
                .build();
    }

    private TextCell createSummaryCellName(String name, Color backgroundColor) {
        return TextCell.builder()
                .text(name)
                .colSpan(2)
                .lineSpacing(1f)
                .borderWidthTop(1)
                .textColor(WHITE)
                .backgroundColor(backgroundColor)
                .font(fonts.get(FontType.OPEN_SANS_ITALIC))
                .fontSize(SUMMARY_TABLE_CELL_FONT_SIZE)
                .horizontalAlignment(LEFT)
                .build();
    }

    private TextCell createSummaryCellValue(String value) {
        return TextCell.builder().text(value).backgroundColor(LIGHT_GRAY)
                .font(fonts.get(FontType.OPEN_SANS_BOLD_ITALIC))
                .verticalAlignment(TOP)
                .build();
    }

    private static String processAddress(String address) {
        return address.replace(", ", System.lineSeparator());
    }

    private static PDFont loadFont(PDDocument document, String filename) throws IOException {
        var resource = new ClassPathResource("fonts/%s.ttf".formatted(filename));
        try (var inputStream = resource.getInputStream()) {
            return PDType0Font.load(document, inputStream);
        }
    }

    private static void drawText(PDPageContentStream content, String text,
                                 PDFont font, int fontSize,
                                 float offsetX, float offsetY
    ) throws IOException {
        content.beginText();
        content.newLineAtOffset(offsetX, offsetY);
        content.setFont(font, fontSize);
        content.showText(text);
        content.endText();
    }

    private static void drawTable(PDDocument document, TableDrawer drawer) throws IOException {
        drawer.draw(() -> document, OrderInvoice::createPage, PADDING);
    }

    private static float calculateTextWidth(String text, PDFont font, int fontSize) throws IOException {
        return font.getStringWidth(text) / 1000 * fontSize;
    }

    private static float getTableMaxWidth(PDPage page) {
        return getPageWidth(page) - 2 * PADDING;
    }

    private static float getPageWidth(PDPage page) {
        return page.getMediaBox().getWidth();
    }

    private static float getPageHeight(PDPage page) {
        return page.getMediaBox().getHeight();
    }

    private static PDPage createPage() {
        return new PDPage(PDRectangle.A4);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private enum FontType {
        OPEN_SANS("OpenSans-Regular"),
        OPEN_SANS_BOLD("OpenSans-Bold"),
        OPEN_SANS_BOLD_ITALIC("OpenSans-BoldItalic"),
        OPEN_SANS_ITALIC("OpenSans-Italic");

        private final String actualName;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private enum PriceType {
        SUBTOTAL("Subtotal"),
        TOTAL("Total due");

        private final String label;
    }
}
