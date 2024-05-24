package asaas.adapter

import asaas.PersonType

class PayerSaveAdapter {

    String name

    String email

    String phone

    String cpfCnpj

    String personType

    String street

    Integer number

    String neighborhood

    String city 

    String state

    String complement

    String CEP

    Long customerId

    public PayerSaveAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.phone = params.phone
        this.cpfCnpj = params.cpfCnpj
        this.personType = PersonType.convert(params.personType)
        this.street = params.street
        this.number = params.number.toInteger()
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = params.state
        this.complement = params.complement
        this.CEP = params.CEP
        this.customerId = params.customerId.toLong()
    }
}