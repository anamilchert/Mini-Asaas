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
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar um pagador: $errorsMessage"
            render(view: "show", params: params)
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

    def delete() {
        try {
            payerService.delete(params.id.toLong())
            flash.message = "Pagador excluído com sucesso"
            redirect(action: "index")
        } catch (Exception exception) {
            flash.message = "Não foi possível excluir pagador"
            redirect(action: "index")
        }
    }
}