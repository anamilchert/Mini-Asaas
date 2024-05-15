package asaas

import asaas.utils.BaseEntity

class Address extends BaseEntity {

    String street

    String number

    String neighborhood

    String city

    String state

    String complement
    
    String CEP

    Customer customer
    Payer payer

    static constraints = {
        street blank: false
        number blank: false, min: 1
        neighborhood blank: false
        city blank: false
        state blank: false
        CEP blank: false, size: 8
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