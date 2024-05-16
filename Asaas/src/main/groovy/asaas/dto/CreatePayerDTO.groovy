package asaas.dto

import asaas.Payer
import asaas.PersonType

class CreatePayerDTO {
  String name

  String email

  String phone

  String cpfCnpj
  
  PersonType personType

  static Payer createPayer(CreatePayerDTO data){
    Payer payer = new Payer()
    payer.name = data.name
    payer.email = data.email
    payer.phone = data.phone
    payer.cpfCnpj = data.cpfCnpj
    payer.personType = data.personType

    return payer
  }

}