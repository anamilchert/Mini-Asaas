package asaas

import asaas.adapter.PaymentAdapter
import asaas.Payer
import asaas.PayerService
import asaas.Payment
import asaas.PaymentService
import asaas.PaymentType
import asaas.repositories.PaymentRepository

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
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)
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

    def update() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)
            redirect(action:"show", id:payment.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar uma cobrança: $errorsMessage"
            render(view: "show", id: params.id)
        }
    }

    def show() {
        Payment payment = PaymentRepository.query([id: params.id.toLong()]).get()
        
        if (payment) {
            return [payment: payment]
        }

        render "Cobrança não encontrada"
    }

    def list() {
        List<Payment> paymentList = PaymentRepository.query([customerId: params.customerId.toLong()]).list()
        return [paymentList: paymentList]
    }

    def fetchAllCustomerAndPayerPayment() {
        List<Payment> paymentList = PaymentRepository
            .query([customerId: params.customerId.toLong(), payerId: params.payerId.toLong()]).list()
        return [paymentList: paymentList]
    }
}
