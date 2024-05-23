package asaas

import asaas.adapter.PaymentSaveAdapter
import asaas.Payer
import asaas.Payment
import asaas.PayerService
import asaas.PaymentService

import grails.validation.ValidationException

class PaymentController {

    def paymentService

    def payerService

    def index() {
        List<Payer> payerList = payerService.list(params.customerId.toLong())
        return [payerList: payerList, customerId:params.customerId]
    }

    def save() {
        try {
            PaymentSaveAdapter paymentSaveAdapter = new PaymentSaveAdapter(params)
            Payment payment = paymentService.save(paymentSaveAdapter)
            redirect(action:"show", id:payment.id)

        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar uma cobrança: $errorsMessage"
            render(view: "show", params: params)
        }
    }

    def show() {
        Payment payment = Payment.read(params.id.toLong())

        if (payment) {
            return [payment: payment]
        }

        render "Cobrança não encontrada"
    }

}
