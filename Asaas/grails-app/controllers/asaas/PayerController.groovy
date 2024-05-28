package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.adapter.PayerUpdateAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payer
import asaas.PayerService

import grails.validation.ValidationException

class PayerController {

    PayerService payerService

    def index() {
        List<Customer> customers = Customer.list()
        return [customers: customers]
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
        List<Payer> payerList = payerService.list(params.customerId.toLong())
        return [payerList: payerList]
    }

    def update() {
        try {
            PayerUpdateAdapter payerUpdateAdapter = new PayerUpdateAdapter(params)
            Payer payer = payerService.update(payerUpdateAdapter, params.id.toLong())
            flash.message = "Os dados foram atualizados com sucesso!"
            redirect(action:"show", id:payer.id)
        } catch (RuntimeException e) {
            flash.message = "Não foi possível atualizar os dados. Por favor, tente novamente."
            redirect(action:"show", id:params.id)
        }
    }
}