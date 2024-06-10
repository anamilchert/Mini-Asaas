package asaas

import asaas.Customer
import asaas.CustomerService
import asaas.repositories.CustomerRepository

import grails.validation.ValidationException

    class CustomerController {

        def customerService

        def index() {
        }

        def save() {
            try {
                Customer customer = customerService.save(params)
                redirect(action: 'show', id: customer.id)
                } catch (ValidationException e) {
                String errorsMessage = e.errors.allErrors.collect { it.defaultMessage }.join(', ')
                flash.error = "Não foi possível salvar sua conta: $errorsMessage"
                render(view: "edit", model: [customer: new Customer(params)])
            }
        }

        def show() {
            Customer customer = CustomerRepository.query([id: params.id.toLong()]).get()  as Customer

            if (!customer) {
                flash.error = 'Conta não encontrada'
                redirect(action: 'index')
                return
            }
            render(view: 'show', model: [customer: customer])
        }

        def edit() {
            Customer customer = CustomerRepository.query([id: params.id.toLong()]).get()  as Customer
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
                }  catch (ValidationException e) {
                String errorsMessage = e.errors.allErrors.collect { it.defaultMessage }.join(', ')
                flash.error = "Não foi possível atualizar sua conta: $errorsMessage"
                render(view: 'edit', model: [customer: customerRepository.query([includeDeleted: false, id: id]).get() as Customer])
            }

     def delete() {
        Long id = params.id?.toLong()
        try {
            customerService.delete(id)
            flash.message = 'Conta deletada com sucesso'
            redirect(action: 'index')
        } catch (ValidationException e) {
            flash.error = "Não foi possível deletar a conta: ${e.message}"
            redirect(action: 'show', id: id)
        }
    }
  }
}