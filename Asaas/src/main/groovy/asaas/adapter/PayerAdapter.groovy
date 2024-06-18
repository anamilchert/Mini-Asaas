package asaas.adapter

import asaas.PersonType

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class PayerAdapter{

    String name

    String email

    String phone

    String cpfCnpj

    PersonType personType

    String street

    Integer number

    String province

    String city 

    String state

    String complement

    String zipCode

    Long customerId

    public PayerAdapter(Map params, Long customerId) {
        this.name = params.name
        this.email = params.email
        this.phone = removeNonNumerics(params.phone as String)
        this.cpfCnpj = removeNonNumerics(params.cpfCnpj as String)
        this.personType = PersonType.convert(params.personType as String)
        this.street = params.street
        this.number = params.number as Integer
        this.province = params.province
        this.city = params.city
        this.state = params.state instanceof String ? params.state.toUpperCase() : null
        this.complement = params.complement
        this.zipCode = removeNonNumerics(params.zipCode as String)
        this.customerId = customerId
    }

    private String removeNonNumerics(String text) {
        return text.replaceAll(/\D/, "")
    }
}