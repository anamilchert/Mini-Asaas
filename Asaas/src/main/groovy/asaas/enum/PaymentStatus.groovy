package asaas

import grails.util.Holders

enum PaymentStatus {
    PENDING,
    CANCELED,
    EXPIRED,
    APPROVED
  
    public static PaymentStatus convert(String paymentStatus) {
        try {
            if (paymentStatus instanceof String) paymentStatus = paymentStatus.toUpperCase()
            return paymentStatus as PaymentStatus
        } catch(Exception exception) {
            return null
        }
    }

    public String getLabel() {
        return Holders.applicationContext.getBean("messageSource").getMessage("PaymentStatus.${this}.label", null, "", new Locale("pt", "BR"))
    }
}