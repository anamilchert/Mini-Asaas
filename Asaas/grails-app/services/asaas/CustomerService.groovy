package asaas

import asaas.Address
import asaas.Customer
import asaas.PersonType
import asaas.utils.DomainUtils

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class CustomerService {

    public Customer save(Map params) {
        Customer customerValues = validateCustomerParams(params)
        if (customerValues.hasErrors()) {
            throw new ValidationException("Erro ao criar a conta", customerValues.errors)
        }

        Customer customer = new Customer(
            name: params.name,
            email: params.email,
            cpfCnpj: params.cpfCnpj,
            personType: PersonType.convert(params.personType)
        )

        Address address = new Address(
            street: params.address.street,
            number: (params.address.number as Integer),
            neighborhood: params.address.neighborhood,
            city: params.address.city,
            state: params.address.state,
            complement: params.address.complement,
            CEP: params.address.CEP
        )

        customer.address = address

        customer.save(failOnError: true)

        return customer
    }

    def update(Long id, Map params) {
        Customer customer = Customer.get(id)
        
        if (!customer) {
            throw new ValidationException("Conta não encontrada")
        }

        customer.name = params.name ?: customer.name
        customer.email = params.email ?: customer.email
        customer.cpfCnpj = params.cpfCnpj ?: customer.cpfCnpj
        customer.personType = params.personType ? PersonType.convert(params.personType) : customer.personType

        if (params.address) {
            customer.address.street = params.address.street ?: customer.address.street
            customer.address.number = params.address.number ? (params.address.number as Integer) : customer.address.number
            customer.address.neighborhood = params.address.neighborhood ?: customer.address.neighborhood
            customer.address.city = params.address.city ?: customer.address.city
            customer.address.state = params.address.state ?: customer.address.state
            customer.address.complement = params.address.complement ?: customer.address.complement
            customer.address.CEP = params.address.CEP ?: customer.address.CEP
        }

        customer.save(failOnError: true)
        return customer
    }

    private Customer validateCustomerParams(Map params) {
        Customer customer = new Customer()
        if (!params.name) {
            DomainUtils.addError(customer, "Nome é obrigatório")
        }
        if (!params.email) {
            DomainUtils.addError(customer, "Email é obrigatório")
        }
        if (!params.cpfCnpj) {
            DomainUtils.addError(customer, "CPF/CNPJ é obrigatório")
        }
        if (!params.address || !params.address.street || !params.address.number ||
            !params.address.neighborhood || !params.address.city || !params.address.state ||
            !params.address.CEP) {
            DomainUtils.addError(customer, "Endereço incompleto")
        }

        return customer
    }
}