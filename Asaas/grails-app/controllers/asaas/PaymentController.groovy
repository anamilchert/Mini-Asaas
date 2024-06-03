package asaas

import asaas.adapter.PaymentSaveAdapter
import asaas.Payer
import asaas.PayerService
import asaas.Payment
import asaas.PaymentService
import asaas.PaymentType

import grails.validation.ValidationException

class PaymentController {

    PaymentService paymentService

    PayerService payerService

    def index() {
        List<Payer> payerList = payerService.list(params.customerId.toLong())
        List<PaymentType> paymentTypes = PaymentType.values()
        return [payerList: payerList, customerId:params.customerId, paymentTypes: paymentTypes]
    }

    def save() {
        try {
            PaymentSaveAdapter paymentSaveAdapter = new PaymentSaveAdapter(params)
            Payment payment = paymentService.save(paymentSaveAdapter)
            redirect(action:"show", id:payment.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar uma cobrança: $errorsMessage"
            redirect(action: "show")
        } catch (Exception exception) {
            flash.message = "Houve um erro inesperado ao tentar salvar uma cobrança. Por favor, tente novamente"
            redirect(action: "index")
        }
    }

    def show() {
        Payment payment = paymentService.getPaymentById(params.id.toLong())

        if (payment) {
            return [payment: payment]
        }

        render "Cobrança não encontrada"
    }

    def list() {
        List<Payment> paymentList = paymentService.listCustomerPayments(params.customerId.toLong())
        return [paymentList: paymentList]
    }

    def fetchAllCustomerAndPayerPayment() {
        List<Payment> paymentList = paymentService.listCustomerAndPayerPayments(params.customerId.toLong(), params.payerId.toLong())
        return [paymentList: paymentList]
    }
}
