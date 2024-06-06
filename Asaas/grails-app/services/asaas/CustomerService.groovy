package asaas

import asaas.Address
import asaas.Customer
import asaas.PersonType
import asaas.utils.DomainUtils
import asaas.adapter.CustomerAdapter

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    public Customer save(CustomerAdapter customerAdapter) {
        Customer customerValues = validateCustomerParams(customerAdapter)
        if (customerValues.hasErrors()) {
            throw new ValidationException("Erro ao criar a conta", customerValues.errors)
        }

        Customer customer = new Customer(
            name: customerAdapter.name,
            email: customerAdapter.email,
            cpfCnpj: customerAdapter.cpfCnpj,
            personType: customerAdapter.personType
        )

        Address address = new Address(
            street: customerAdapter.street,
            number: customerAdapter.number,
            province: customerAdapter.province,
            city: customerAdapter.city,
            state: customerAdapter.state,
            complement: customerAdapter.complement,
            zipCode: customerAdapter.zipCode
        )

        customer.address = address

        customer.save(failOnError: true)

        return customer
    }

    public Customer update(Long id, Map params) {
        Customer customer = Customer.get(id)
        
        if (!customer) {
            throw new RuntimeException("Conta não encontrada")
        }

        CustomerAdapter adapter = new CustomerAdapter(params)

        customer.name = adapter.name ?: customer.name
        customer.email = adapter.email ?: customer.email
        customer.cpfCnpj = adapter.cpfCnpj ?: customer.cpfCnpj
        customer.personType = adapter.personType ?: customer.personType

            customer.address.street = adapter.street ?: customer.address.street
            customer.address.number = adapter.number ?: customer.address.number
            customer.address.province = adapter.province ?: customer.address.province
            customer.address.city = adapter.city ?: customer.address.city
            customer.address.state = adapter.state ?: customer.address.state
            customer.address.complement = adapter.complement ?: customer.address.complement
            customer.address.zipCode = adapter.zipCode ?: customer.address.zipCode

        customer.save(failOnError: true)
        return customer
    }

    private Customer validateCustomerParams(CustomerAdapter customerAdapter) {
        Customer customer = new Customer()
        if (!customerAdapter.name) {
            DomainUtils.addError(customer, "Nome é obrigatório")
        }
        if (!customerAdapter.email) {
            DomainUtils.addError(customer, "Email é obrigatório")
        }
        if (!customerAdapter.cpfCnpj) {
            DomainUtils.addError(customer, "CPF/CNPJ é obrigatório")
        }
        if (!customerAdapter.street || !customerAdapter.number || !customerAdapter.province ||
            !customerAdapter.city || !customerAdapter.state || !customerAdapter.zipCode) {
            DomainUtils.addError(customer, "Endereço incompleto")
        }

        return customer
    }
}