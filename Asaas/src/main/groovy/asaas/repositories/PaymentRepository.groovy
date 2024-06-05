package asaas.repositories

import asaas.Payment

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria

@GrailsCompileStatic
class PaymentRepository {

    static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where({})

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

            if (search.containsKey('payerId')) {
                eq('payer.id', search.payerId)
            }
        }

        return query
    }
}