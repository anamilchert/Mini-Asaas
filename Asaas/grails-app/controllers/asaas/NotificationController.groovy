package asaas

import asaas.BaseController
import asaas.Customer
import asaas.NotificationService
import asaas.Notification

import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured

@Secured('IS_AUTHENTICATED_FULLY')
class NotificationController extends BaseController {

    NotificationService notificationService
    
    def list() {
        List<Notification> notificationList = notificationService.listAllNotifications(getCurrentCustomerId())
        render(template: "/templates/bell", model: [notificationList: notificationList])
    }
}