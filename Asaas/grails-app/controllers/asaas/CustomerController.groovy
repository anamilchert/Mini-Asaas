package asaas

import asaas.Customer
import asaas.CustomerService
import asaas.adapter.CustomerAdapter

import grails.validation.ValidationException

class CustomerController {

    def customerService

    def index() {
    }

    def save() {
        try {
            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            Customer customer = customerService.save(customerAdapter)

            redirect(action: 'show', id: customer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = e.errors.allErrors.collect { it.defaultMessage }.join(', ')
            flash.error = "Não foi possível salvar sua conta: $errorsMessage"
            render(view: 'index', model: [customer: new Customer(params)])
        }
    }

    def show() {
        Long id = params.id?.toLong()
        Customer customer = Customer.read(id)

        if (!customer) {
            flash.error = 'Conta não encontrada'
            redirect(action: 'index')
            return
        }
        render(view: 'show', model: [customer: customer])
    }

    def edit() {
        Long id = params.id?.toLong()
        Customer customer = Customer.get(id)

        if (!customer) {
            flash.error = 'Conta não encontrada'
            redirect(action: 'index')
            return
        }
        render(view: 'edit', model: [customer: customer])
    }

    def update() {
        Long id = params.id?.toLong()
        try {
            Customer customer = customerService.update(id, params)
            flash.message = 'Conta atualizada com sucesso'
            redirect(action: 'show', id: customer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.collect { it.defaultMessage }.join(', ')
            flash.error = "Não foi possível atualizar sua conta: $errorsMessage"
            render(view: 'edit', model: [customer: Customer.get(id)])
        }
    }

}