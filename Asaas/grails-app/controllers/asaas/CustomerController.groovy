package asaas

import asaas.Customer
import asaas.CustomerService
import grails.validation.ValidationException

class CustomerController {

    def customerService

    def index() {
        
    }

    def save() {
        try {
            Customer customer = customerService.save(params)
            redirect(action: "show", id: customer.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar sua conta: $errorsMessage"
            render(view: 'show', params: params)
        }
    }

    def show(Long id) {
        Customer customer = Customer.read(id)
        if (customer) {
            render(view: 'show', model: [customer: customer])
        } else {
            flash.error = "Conta não encontrada"
            redirect(action: 'index')
        }
    }
}
