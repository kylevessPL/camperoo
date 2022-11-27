package pl.piasta.camperoo.infrastructure.email;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EmailTemplates {
    public static final String ORDER_INVOICE = "order-invoice-email";
    public static final String ORDER_STATUS_CHANGE = "order-status-change-email";
    public static final String PASSWORD_RESET = "password-reset-email";
}
