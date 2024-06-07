package asaas.repositories

import asaas.Payer

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria

@GrailsCompileStatic
class PayerRepository {

    static DetachedCriteria<Payer> query(Map search) {
        DetachedCriteria<Payer> query = Payer.where({})

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
        }

        return query
    }
}