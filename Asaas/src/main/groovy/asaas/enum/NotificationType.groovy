package asaas

import grails.util.Holders

enum NotificationType {
    PAYMENT_OVERDUE,
    PAYMENT_RECEIVED,
  
    public static NotificationType convert(String notificationType) {
        try {
            if (notificationType instanceof String) notificationType = notificationType.toUpperCase()
            return notificationType as NotificationType
        } catch(Exception exception) {
            return null
        }
    }

    public String getLabel() {
        return Holders.applicationContext.getBean("messageSource")
            .getMessage("NotificationType.${this}.label", null, "", new Locale("pt", "BR"))
    }
}