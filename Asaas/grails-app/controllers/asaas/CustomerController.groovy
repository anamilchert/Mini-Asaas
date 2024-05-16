package asaas

import grails.validation.ValidationException

class CustomerController {

    def customerService

    def save() {
        try {
            Customer customer = customerService.save(params)
            redirect(action: "show", id: customer.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar sua conta: $errorsMessage"
            render(view: 'createCustomer', params: params)
        }
    }

    def show(Long id) {
        Customer customer = Customer.get(id)
        if (customer) {
            render(view: 'showCustomer', model: [customer: customer])
        } else {
            flash.error = "Conta não encontrada"
            redirect(action: 'index')
        }
    }
}
