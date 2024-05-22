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
                    if (!customer) {
                    flash.error = "Conta não encontrada"
                    redirect(action: 'index')

                    return 
                }

                render(view: 'show', model: [customer: customer])
        }

        def edit(Long id) {
        Customer customer = Customer.get(id)
        if (!customer) {
            flash.error = "Conta não encontrada"
            redirect(action: 'index')
            return
        }
        
        render(view: 'edit', model: [customer: customer])
        }

        def update(Long id) {
            Customer customer = Customer.get(id)
            if (!customer) {
                flash.error = "Conta não encontrada"
                redirect(action: 'index')

                return
            }

            customer.properties = params

            try {
                customerService.save(customer)
                flash.message = "Conta atualizada com sucesso"
                redirect(action: "show", id: customer.id)

            } catch (ValidationException e) {
                String errorsMessage = e.errors.allErrors.collect { it.defaultMessage }.join(", ")
                flash.error = "Não foi possível atualizar sua conta: $errorsMessage"

                render(view: 'show', model: [customer: customer])
            }
        }   
}
