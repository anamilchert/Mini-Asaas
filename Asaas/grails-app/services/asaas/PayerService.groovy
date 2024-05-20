package asaas

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import asaas.Address
import asaas.Customer
import asaas.Payer
import asaas.PersonType

@Transactional
class PayerService {

  public Payer save(Map params){
    Map parsedParams = parseSaveParams(params)
    Payer validatedPayer = validationSave(params)

    if(validatedPayer.hasErrors()){
      throw new ValidationException("Erro ao criar um pagador", validatedPayer.errors)
    }
    
    Address address = new Address()
    address.street = parsedParams.street
    address.number = parsedParams.number
    address.neighborhood = parsedParams.neighborhood
    address.city = parsedParams.city
    address.state = parsedParams.state
    address.complement = parsedParams.complement
    address.CEP = parsedParams.CEP

    Customer customer = Customer.load(parsedParams.customerId)

    Payer payer = new Payer()
    payer.name = parsedParams.name
    payer.email = parsedParams.email
    payer.cpfCnpj = parsedParams.cpfCnpj
    payer.phone = parsedParams.phone
    payer.personType = parsedParams.personType
    payer.address = address
    payer.customer = customer
    
    payer.save(failOnError: true)

    return payer
  }

  public Payer getPayer(Long payerId){
    Payer payer = Payer.read(payerId)
    return payer
  }

  public List<Payer> list(Long customerId){
    List<Payer> payerList = Payer.query(customerId: customerId).list()
    return payerList
  }

  private Map parseSaveParams(Map params) {
    Map parsedParams = [:]

    parsedParams.name = params.name
    parsedParams.email = params.email
    parsedParams.cpfCnpj = params.cpfCnpj
    parsedParams.phone = params.phone
    parsedParams.personType = params.personType instanceof String ? PersonType.convert(params.personType) : params.personType
    parsedParams.street = params.street
    parsedParams.number = params.number ? (params.number as Integer): null
    parsedParams.neighborhood = params.neighborhood 
    parsedParams.city = params.city
    parsedParams.state = params.state
    parsedParams.complement = params.complement
    parsedParams.CEP = params.CEP
    parsedParams.customerId = params.customerId ? (params.customerId as Long) : null

    return parsedParams
  }

  private Payer validationSave(Map params){
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
