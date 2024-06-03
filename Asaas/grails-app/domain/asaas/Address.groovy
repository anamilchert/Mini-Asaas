package asaas

import asaas.utils.BaseEntity

class Address extends BaseEntity {

    String street

    Integer number

    String neighborhood

    String city

    String state

    String complement

    String zipCode

    static constraints = {
        street blank: false
        number blank: false, min: 1
        neighborhood blank: false
        city blank: false
        state blank: false
        zipCode blank: false, size: 8..8
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