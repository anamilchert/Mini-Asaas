package asaas

import asaas.utils.BaseEntity

class Address extends BaseEntity {

    String street
    String city
    String state
    String number
    String complement
    String CEP

    static constraints = {
        street blank: false
        city blank: false
        state blank: false
        number blank: false, min: 1
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