package asaas.adapter

import asaas.PersonType
import asaas.utils.Utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CustomerAdapter {

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

    public CustomerAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.phone = Utils.removeNonNumerics(params.phone.toString())
        this.cpfCnpj = Utils.removeNonNumerics(params.cpfCnpj.toString())
        this.personType = PersonType.convert(params.personType as String)
        this.street = params.street
        this.number = params.number as Integer
        this.province = params.province
        this.city = params.city
        this.state = params.state
        this.complement = params.complement
        this.zipCode = Utils.removeNonNumerics(params.zipCode.toString())
    }
}