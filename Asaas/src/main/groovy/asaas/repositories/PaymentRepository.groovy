package asaas.repositories

import asaas.Payment
import asaas.PaymentStatus

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria

@GrailsCompileStatic
class PaymentRepository {

    static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where({})

        if (!search.ignoreCustomer && !search.customerId) {
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

            if (search.containsKey('payerId')) {
                eq('payer.id', search.payerId)
            }

            if (search.containsKey('status')) {
                eq('status', PaymentStatus.valueOf(search.status.toString()))
            }

            if (search.containsKey('dueDate[ge]')) {
                ge('dueDate', search['dueDate[ge]'])
            }

            if (search.containsKey('dueDate[le]')) {
                le('dueDate', search['dueDate[le]'])
            }
        }

        return query
    }
}