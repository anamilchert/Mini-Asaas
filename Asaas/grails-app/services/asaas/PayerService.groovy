package asaas

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import asaas.Customer
import asaas.Address
import asaas.Payer
import asaas.PersonType

@Transactional
class PayerService {
  def save(Map params){

    Payer payerValidation = validation(params)

    if(payerValidation.hasErrors()){
      throw new ValidationException("Erro ao criar um pagador", payerValidation.errors)
    }
    
    Address address = new Address(
      street: params.street,
      number: params.number,
      neighborhood: params.neighborhood,
      city: params.city,
      state: params.state,
      complement: params.complement,
      CEP: params.CEP
    )

    Customer customer = Customer.load(params.customerId)

    Payer payer = new Payer(
      name: params.name,
      email: params.email,
      cpfCnpj: params.cpfCnpj,
      phone: params.phone,
      personType: PersonType.convert(params.personType)
    )

    payer.address = address
    payer.customer = customer
    
    payer.save(flush: true, failOnError: true)

    return payer
  }



  private Payer validation(Map params){
    Payer payer = new Payer()
    
    if(!params.name){
      payer.errors.reject("name", null, "Nome é obrigatório")
    }

    if(!params.email){
      payer.errors.reject("email", null, "Email é obrigatório")
    }

    if(!params.phone){
      payer.errors.reject("phone", null, "Phone é obrigatório")
    }

    if(!params.cpfCnpj){
      payer.errors.reject("cpfCnpj", null, "CPF/CNPJ é obrigatório")
    }

    if (!params.street || !params.number || 
            !params.neighborhood || !params.city || !params.state || 
            !params.CEP) 
      {
        payer.errors.reject("address", null, "Endereço incompleto")
      }

    return payer
  }

}
