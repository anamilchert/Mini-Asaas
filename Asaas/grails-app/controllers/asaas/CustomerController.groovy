package asaas

import asaas.BaseController
import asaas.Customer
import asaas.CustomerService

import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured

class CustomerController extends BaseController {

    def customerService
    
    @Secured("permitAll()")    
    def index() {
    }

    @Secured("permitAll()")
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

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    def update() {
        Long id = params.id?.toLong()
        try {
            Customer customer = customerService.update(id, params)
            flash.message = 'Conta atualizada com sucesso'
            redirect(action: 'show', id: customer.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.collect { it.defaultMessage }.join(', ')
            flash.error = "Não foi possível atualizar sua conta: $errorsMessage"
            render(view: 'edit', model: [customer: Customer.get(id)])
        }
    }
}