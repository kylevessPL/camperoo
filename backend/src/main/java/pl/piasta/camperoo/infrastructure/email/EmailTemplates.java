package pl.piasta.camperoo.infrastructure.email;

import lombok.experimental.UtilityClass;

@UtilityClass
class EmailTemplates {
    public final String ORDER_INVOICE = "order-invoice-email";
    public final String ORDER_STATUS_CHANGE = "order-status-change-email";
    public final String PASSWORD_RESET = "password-reset-email";
}
