package asaas

import asaas.utils.BaseEntity
import asaas.PaymentStatus
import asaas.PaymentType
import asaas.Customer
import asaas.Payer

class Payment extends BaseEntity {
  
    BigDecimal value

    Date dueDate

    PaymentStatus status

    PaymentType type

    Customer customer

    Payer payer

    static constraints = {
        value  min: BigDecimal.ZERO
        status validator: { val, obj -> return val in PaymentStatus.values() }
        type validator: { val, obj -> return val in PaymentType.values() }
    }

    static namedQueries = {
        query { search ->
            if (!Boolean.valueOf(search.includeDeleted)) {
                eq('deleted', false)
            }

            if (search.containsKey('id')) {
                eq('id', search.id)
            }
        }
    }
}