package asaas

import asaas.Customer
import asaas.Address
import grails.validation.ValidationException

class CustomerService {

    public Customer save(Map params) {
        Customer customer = create(params)
        return customer
    }

    public Customer create(Map params) {

        Customer customerValues = validateCustomerParams(params)

        if (customerValues.hasErrors()) {
            throw new ValidationException("Erro ao criar a conta", customerValues.errors)
        }

        Customer customer = new Customer(
            name: params.name,
            email: params.email,
            cpfCnpj: params.cpfCnpj,
            personType: params.personType
        )

        Address address = new Address(
            street: params.address.street,
            number: params.address.number,
            neighborhood: params.address.neighborhood,
            city: params.address.city,
            state: params.address.state,
            complement: params.address.complement,
            CEP: params.address.CEP
        )

        customer.address = address

        customer.save(flush: true, failOnError: true)

        return customer
    }

    private Customer validateCustomerParams(Map params) {
   
        Customer customer = new Customer()

        if (!params.name) {
            customer.errors.reject("name", null, "Nome é obrigatório")
        }

        if (!params.email) {
            customer.errors.reject("email", null, "Email é obrigatório")
        }

        if (!params.cpfCnpj) {
            customer.errors.reject("cpfCnpj", null, "CPF/CNPJ é obrigatório")
        }

        if (!params.address || !params.address.street || !params.address.number || 
            !params.address.neighborhood || !params.address.city || !params.address.state || 
            !params.address.CEP) {
            customer.errors.reject("address", null, "Endereço incompleto")
        }

        return customer
    }
}
