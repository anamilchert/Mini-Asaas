package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payer
import asaas.PayerService

import grails.validation.ValidationException

class PayerController {

    PayerService payerService

    def index() {
        List<Customer> customerList = Customer.list()
        return [customerList: customerList]
    }

    def save() {
        try {
            PayerSaveAdapter payerSaveAdapter = new PayerSaveAdapter(params)
            Payer payer = payerService.save(payerSaveAdapter)
            redirect(action:"show", id:payer.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar um pagador: $errorsMessage"
            render(view: "show", params: params)
        } catch (RuntimeException e) {
            render(view: "show", params: params)
        }
    }

    def show() {
        Payer payer = Payer.read(params.id.toLong())

        if (payer) return [payer: payer]

        render "Pagador não encontrado"
    }

    def list() {
        List<Payer> payerList = payerService.listByCustomer(params.customerId.toLong())
        return [payerList: payerList]
    }
}