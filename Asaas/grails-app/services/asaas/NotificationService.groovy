package asaas

import asaas.Customer
import asaas.Notification
import asaas.NotificationType
import asaas.NotificationStatus 
import asaas.repositories.NotificationRepository
import asaas.Payment

import grails.gorm.transactions.Transactional

@Transactional
class NotificationService {

    public void create(NotificationType type, Long customerId, Long paymentId) {
        Notification notification = new Notification()
        notification.type = type
        notification.customer = Customer.load(customerId)
        notification.payment = Payment.load(paymentId)
        notification.status = NotificationStatus.UNREAD

        notification.save(failOnError: true)
    }

    public List<Notification> listAllNotifications(Long customerId) {
        List<Notification> notificationList = NotificationRepository
            .query([customerId: customerId]).list(max: 10) as List<Notification>
        return notificationList
    }

    public List<Notification> listUnreadNotifications(Long customerId) {
        List<Notification> notificationUnreadList = NotificationRepository
            .query([customerId: customerId, status: NotificationStatus.UNREAD]).list() as List<Notification>
        println notificationUnreadList
        return notificationUnreadList
    }

    public void markAsRead(Long notificationId, Long customerId) {
        Notification notification = NotificationRepository.query([customerId: id, id: notificationId]).get() as Notification
        notification.status = NotificationStatus.READ
        notification.save(flush: true)
    }
}