package asaas

import javax.persistence.EnumType
import javax.persistence.Enumerated

import asaas.utils.BaseEntity
import asaas.PaymentStatus
import asaas.PaymentType

class Payment extends BaseEntity {
  Integer value
  Date maturityDate

  @Enumerated(EnumType.STRING)
  PaymentStatus status

  @Enumerated(EnumType.STRING)
  PaymentType method

  static constraints = {
    value  min: 0
    status validator: { val, obj ->
            return val in PaymentStatus.values()
        }
    method validator: { val, obj ->
            return val in PaymentType.values()
        }
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