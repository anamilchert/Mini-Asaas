package asaas

import grails.gorm.transactions.Transactional
import asaas.Customer
import asaas.Address
import asaas.Payer
import asaas.AddressService
import asaas.dto.CreatePayerFormDTO
import asaas.dto.CreatePayerDTO

@Transactional
class PayerService {
  AddressService addressService


  def save(CreatePayerFormDTO data, Customer customer){
    Address address = addressService.save(data.address)
    Payer payer = CreatePayerDTO.createPayer(data.payer)
    payer.address = address
    payer.customer = customer
    payer.save(failOnError: true)
    return payer
  }
}
