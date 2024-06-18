package asaas.repositories

import asaas.Notification
import asaas.NotificationStatus

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria

@GrailsCompileStatic
class NotificationRepository {

    static DetachedCriteria<Notification> query(Map search) {
        DetachedCriteria<Notification> query = Notification.where({})
        
        if (!search.customerId) {
            throw new RuntimeException("Customer deve ser informado")
        }

        query = query.where {
            if (!search.includeDeleted ?: false) {
                eq('deleted', false)
            }

            if (search.containsKey('id')) {
                eq('id', search.id)
            }

            if (search.containsKey('customerId')) {
                eq('customer.id', search.customerId)
            }

            if (search.containsKey('status')) {
                eq('status', NotificationStatus.valueOf(search.status.toString()))
            }
        }
        
        return query
    }
}