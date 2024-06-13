package asaas

import asaas.adapter.PaymentAdapter
import asaas.Payer
import asaas.PayerService
import asaas.Payment
import asaas.PaymentService
import asaas.PaymentType
import asaas.repositories.PayerRepository
import asaas.repositories.PaymentRepository

import grails.validation.ValidationException

class PaymentController {

    PaymentService paymentService

    PayerService payerService

    def index() {
        List<Payer> payerList = PayerRepository.query([customerId: params.customerId.toLong()]).list()
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
            redirect(action: "index",  params: [customerId: params.customerId])
        } catch (Exception exception) {
            flash.error = "Houve um erro inesperado ao tentar salvar uma cobrança. Por favor, tente novamente"
            redirect(action: "index",  params: [customerId: params.customerId])
        }
    }

    def update() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.update(paymentAdapter, params.id.toLong())
            flash.message = "Cobrança atualizada com sucesso"
            redirect(action:"show", id:payment.id)
        } catch (ValidationException e) {
            String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível atualizar uma cobrança: $errorsMessage"
            redirect(action: "show", id: params.id)
        } catch (RuntimeException runtimeException) {
            flash.error = "Houve um erro ao tentar atualizar uma cobrança. Por favor, tente novamente"
            redirect(action: "show", id: params.id)
        } catch (Exception exception) {
            flash.error = "Houve um erro inesperado ao tentar atualizar uma cobrança. Por favor, tente novamente"
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        Payment payment = PaymentRepository.query([id: params.id.toLong()]).get()
        
        if (payment) {
            List<PaymentType> paymentTypeList = PaymentType.values()
            return [payment: payment, paymentTypeList:paymentTypeList]
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

    def delete() {
        try {
            paymentService.delete(params.id.toLong())
            flash.message = "Cobrança cancelada com sucesso"
        } catch (RuntimeException runtimeException) {
            flash.error = runtimeException.getMessage()
        } catch (Exception exception) {
            flash.error = "Erro ao cancelar a cobrança. Por favor, contate o time de suporte"
        } finally {
            redirect(action: "show", id: params.id)
        }
    }
}
