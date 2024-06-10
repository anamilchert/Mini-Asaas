package asaas.repositories

import asaas.Customer

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria

@GrailsCompileStatic
class CustomerRepository {

    static DetachedCriteria<Customer> query(Map search) {
        DetachedCriteria<Customer> query = Customer.where({})

        query = query.where {
            if (!search.includeDeleted ?: false) {
                eq('deleted', false)
            }

            if (search.containsKey('id')) {
                eq('id', search.id)
            }
        }

        return query
    }
}