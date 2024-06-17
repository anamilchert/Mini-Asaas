package asaas 

import asaas.Customer
import asaas.Payment
import asaas.NotificationStatus
import asaas.NotificationType
import asaas.utils.BaseEntity

class Notification extends BaseEntity {

    NotificationType type

    NotificationStatus status

    Customer customer
    
    Payment payment

    static constraints = {
        type validator: { val, obj -> return val in NotificationType.values() }
        status validator: { val, obj -> return val in NotificationStatus.values() }
    }

    static namedQueries = {
        query { search ->
            if (!Boolean.valueOf(search.includeDeleted)) {
                eq('deleted', false)
            }

            if (search.containsKey('id')) {
                eq('id', search.id)
            }

            if (search.containsKey('customerId')) {
                eq('customer.id', search.customerId.toLong())
            }
        }
    }
}
