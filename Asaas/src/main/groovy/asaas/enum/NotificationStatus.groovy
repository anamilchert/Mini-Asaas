package asaas

import grails.util.Holders

enum NotificationStatus {
    UNREAD,
    READ,

    public static NotificationStatus convert(String notificationStatus) {
        try {
            if (notificationStatus instanceof String) notificationStatus = notificationStatus.toUpperCase()
            return notificationStatus as NotificationStatus
        } catch(Exception exception) {
            return null
        }
    }

    public String getLabel() {
        return Holders.applicationContext.getBean("messageSource")
            .getMessage("NotificationStatus.${this}.label", null, "", new Locale("pt", "BR"))
    }
}