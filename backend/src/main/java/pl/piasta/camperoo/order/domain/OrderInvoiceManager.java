package pl.piasta.camperoo.order.domain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import pl.piasta.camperoo.file.domain.File;

@RequiredArgsConstructor
class OrderInvoiceManager {
    private static final String FILENAME_PATTERN = "invoice_%d.pdf";
    private final OrderFileRepository fileRepository;

    public File generateInvoice(Order order) {
        var invoiceContent = createInvoice(order);
        var invoice = File.builder()
                .content(invoiceContent)
                .contentType(MediaType.APPLICATION_PDF_VALUE)
                .name(FILENAME_PATTERN.formatted(order.getId()))
                .build();
        return fileRepository.save(invoice);
    }

    @SneakyThrows
    private byte[] createInvoice(Order order) {
        try (var invoice = new OrderInvoice(order)) {
            return invoice.getRawData();
        }
    }
}
