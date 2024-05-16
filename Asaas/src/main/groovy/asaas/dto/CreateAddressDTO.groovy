package asaas.dto

import asaas.Address

class CreateAddressDTO {
  String street

  Integer number

  String neighborhood

  String city

  String state

  String complement

  String CEP

  static Address createAddress(CreateAddressDTO data){
    Address address = new Address()
    address.street = data.street
    address.number = data.number
    address.state = data.state
    address.neighborhood = data.neighborhood
    address.city = data.city
    address.complement = data.complement
    address.CEP = data.CEP

    return address
  }
  
}