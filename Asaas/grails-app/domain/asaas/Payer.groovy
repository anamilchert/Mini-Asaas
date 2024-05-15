package asaas 

import asaas.utils.BaseEntity
import asaas.PersonType
import asaas.Customer

class Payer extends BaseEntity {

  String name

  String email

  String phone

  String cpfCnpj
  
  PersonType personType

  Address address

  Customer customer

  static constraints = {
    name  blank:false
    email blank:false, email:true, unique: true
    phone blank:false, size: 11..11
    cpfCnpj blank:false, unique:true, size:11..14
    personType blank: false
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

