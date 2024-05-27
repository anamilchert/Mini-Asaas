package asaas.adapter

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class PayerUpdateAdapter {

    String email

    String phone 

    String street

    Integer number

    String neighborhood

    String city 

    String state

    String complement

    String CEP

    public PayerSaveAdapter(Map params) {
        this.email = params.email
        this.phone = params.phone
        this.street = params.street
        this.number = params.number as Integer
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = params.state
        this.complement = params.complement
        this.CEP = params.CEP 
    }
}