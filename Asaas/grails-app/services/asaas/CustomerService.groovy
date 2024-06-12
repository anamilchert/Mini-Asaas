package asaas

import asaas.Address
import asaas.Customer
import asaas.PersonType
import asaas.utils.DomainUtils
import asaas.utils.CpfCnpjUtils
import asaas.adapter.CustomerAdapter

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    public Customer save(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = validateSave(customerAdapter)
        if (validatedCustomer.hasErrors()) {
            throw new ValidationException("Erro ao criar a conta", validatedCustomer.errors)
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

        customer.address.street = adapter.street
        customer.address.number = adapter.number
        customer.address.province = adapter.province
        customer.address.city = adapter.city
        customer.address.state = adapter.state
        customer.address.complement = adapter.complement
        customer.address.zipCode = adapter.zipCode
        customer.save(failOnError: true)
        return customer
    }

    private Customer validateSave(CustomerAdapter customerAdapter) {
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

        validateAddress(customerAdapter, customer)

        return customer
    }

    private void validateAddress(CustomerAdapter customerAdapter, Customer customer) {
        if (!customerAdapter.street) {
            DomainUtils.addError(customer, "Rua é obrigatória")
            return
        }

        if (!customerAdapter.number) {
            DomainUtils.addError(customer, "Número é obrigatório")
            return
        }

        if (!customerAdapter.province) {
            DomainUtils.addError(customer, "Bairro é obrigatório")
            return
        }

        if (!customerAdapter.city) {
            DomainUtils.addError(customer, "Cidade é obrigatória")
            return
        }

        if (!customerAdapter.state) {
            DomainUtils.addError(customer, "Estado é obrigatório")
            return
        }

        if (!customerAdapter.zipCode) {
            DomainUtils.addError(customer, "CEP é obrigatório")
            return
        }
    }
}