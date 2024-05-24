package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payer
import asaas.PayerService

import grails.validation.ValidationException

class PayerController {

    def payerService
  
    def index() {
        List<Customer> customers = Customer.list()
        [customers: customers]
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
    }
  }

  def show() {
    Payer payer = Payer.read(params.id.toLong())

        if (payer) {
            return [payer: payer]
        }

        render "Pagador não encontrado"
    }

    def list() {
        List<Payer> payerList = payerService.list(params.customerId.toLong())
        return [payerList: payerList]
    }
}