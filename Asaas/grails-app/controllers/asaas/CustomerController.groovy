package asaas

import asaas.adapter.UserAdapter
import asaas.BaseController
import asaas.Customer
import asaas.CustomerService
import asaas.adapter.CustomerAdapter

import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured

class CustomerController extends BaseController {

    def customerService
    
    @Secured("isAnonymous()")    
    def index() {
    }

    @Secured("isAnonymous()")  
    def save() {
        try {
            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            UserAdapter userAdapter = new UserAdapter(params)
            Customer customer = customerService.save(customerAdapter, userAdapter)
            redirect(action: 'show', id: customer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.collect { it.defaultMessage }.join(', ')
            flash.error = "Não foi possível salvar sua conta: $errorsMessage"
            render(view: 'index', model: [customer: new Customer(params)])
        }
    }

    @Secured("ROLE_ADMIN")
    def show() {
        Customer customer = Customer.read(getCurrentCustomerId())
        return [customer: customer]
    }

    @Secured("ROLE_ADMIN")
    def edit() {
        Customer customer = Customer.read(getCurrentCustomerId())
        return [customer: customer]
    }

    @Secured("ROLE_ADMIN")
    def update() {
        try {
            Long customerId = getCurrentCustomerId() as Long
            Customer customer = customerService.update(customerId, params)
            flash.message = "Conta atualizada com sucesso"
            redirect(action: "show", id: customer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.collect { it.defaultMessage }.join(', ')
            flash.error = "Não foi possível atualizar sua conta: $errorsMessage"
            redirect(action: "edit")
        }
    }
}