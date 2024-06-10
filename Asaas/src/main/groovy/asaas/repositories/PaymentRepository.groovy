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

            if (search.containsKey('status')) {
                eq('status', search.status)
            }

            if (search.containsKey('dueDateFrom') && search.containsKey('dueDateTo')) {
                between('dueDate', search.dueDateFrom, search.dueDateTo)
            } else if (search.containsKey('dueDateFrom')) {
                ge('dueDate', search.dueDateFrom)
            } else if (search.containsKey('dueDateTo')) {
                le('dueDate', search.dueDateTo)
            }
        }

        return query
    }
}