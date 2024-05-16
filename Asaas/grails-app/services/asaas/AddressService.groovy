package asaas

import grails.gorm.transactions.Transactional
import asaas.Address
import asaas.dto.CreateAddressDTO

@Transactional
class AddressService {
  def save(CreateAddressDTO data){
    try {
      Address address = CreateAddressDTO.createAddress(data)
      address.save(failOnError: true)
      return address
    } catch (Exception e){
      println e
      return "Error when trying to create a payer"
    }
  }
} 
