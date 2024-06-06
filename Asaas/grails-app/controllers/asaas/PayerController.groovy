package asaas

import asaas.adapter.PayerAdapter
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
            PayerAdapter payerAdapter = new PayerAdapter(params)
            Payer payer = payerService.save(payerAdapter)
            redirect(action:"show", id:payer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar um pagador: $errorsMessage"
            redirect(view: "index")
        } catch (RuntimeException runtimeException) {
            flash.message = "Ocorreu um erro ao tentar criar um pagador. Por favor, tente novamente"
            redirect(action: "index")
        } catch (Exception exception) {
            flash.message = "Ocorreu um erro inesperado ao tentar criar um pagador. Por favor, tente novamente"
            redirect(action: "index")
        }
    }

    def show() {
        Payer payer = Payer.read(params.id.toLong())

        if (!payer || payer.deleted) {
            flash.message = "Pagador não encontrado"
            redirect(action: "index")
        }

        return [payer: payer]
    }

    def list() {
        List<Payer> payerList = Payer.query(customerId: params.customerId.toLong()).list()
        return [payerList: payerList]
    }

    def update() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            Payer payer = payerService.update(payerAdapter, params.id.toLong())
            flash.message = "Os dados foram atualizados com sucesso!"
            redirect(action:"show", id:payer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível atualizar os dados: $errorsMessage"
            redirect(action: "show", id: params.id)
        } catch (RuntimeException runtimeException) {
            flash.message = "Não foi possível atualizar os dados. Por favor, tente novamente."
            redirect(action:"show", id:params.id)
        } catch (Exception exception) {
            flash.message = "Ocorreu um error inesperado. Por favor, tente novamente."
            redirect(action:"show", id:params.id)
        }
    }
}