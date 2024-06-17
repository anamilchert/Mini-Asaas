package asaas

import asaas.Customer
import asaas.Notification
import asaas.NotificationType
import asaas.NotificationStatus 
import asaas.Payment

import grails.gorm.transactions.Transactional

@Transactional
class NotificationService {

    public create(NotificationType type, Long customerId, Long paymentId) {
        Notification notification = new Notification()
        notification.type = type
        notification.customer = Customer.load(customerId)
        notification.payment = Payment.load(paymentId)
        notification.status = NotificationStatus.UNREAD

        notification.save(failOnError: true)
    } 
}