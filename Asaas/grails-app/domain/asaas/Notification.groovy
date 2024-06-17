package asaas 

import asaas.utils.BaseEntity
import asaas.NotificationType
import asaas.NotificationStatus

class Notification extends BaseEntity {

    NotificationType type
    NotificationStatus status

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
