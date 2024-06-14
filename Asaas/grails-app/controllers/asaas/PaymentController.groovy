package asaas

import asaas.adapter.PaymentAdapter
import asaas.BaseController
import asaas.Payer
import asaas.PayerService
import asaas.Payment
import asaas.PaymentService
import asaas.PaymentType
import asaas.repositories.PayerRepository
import asaas.repositories.PaymentRepository

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

@Secured('IS_AUTHENTICATED_FULLY')
class PaymentController extends BaseController{

    PaymentService paymentService

    PayerService payerService

    def index() {
        try {
            List<Payer> payerList = PayerRepository.query([customerId: getCurrentCustomerId()]).list()
            List<PaymentType> paymentTypes = PaymentType.values()
            return [payerList: payerList, paymentTypes: paymentTypes]
        } catch (RuntimeException runtimeException) {
            flash.message = runtimeException.getMessage()
            redirect(uri: "/")
        } catch (Exception exception) {
            flash.message = "Erro inesperado. Por favor, contate o suporte"
            redirect(uri: "/")
        }
    }

    def save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params, getCurrentCustomerId())
            Payment payment = paymentService.save(paymentAdapter)
            redirect(action:"show", id:payment.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar uma cobrança: $errorsMessage"
            redirect(action: "index")
        } catch (Exception exception) {
            flash.error = "Houve um erro inesperado ao tentar salvar uma cobrança. Por favor, tente novamente"
            redirect(action: "index")
        }
    }

    def update() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params, getCurrentCustomerId())
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
        try {
            Payment payment = PaymentRepository.query([customerId: getCurrentCustomerId(), id: params.id.toLong()]).get()

            if (payment) {
                List<PaymentType> paymentTypeList = PaymentType.values()
                return [payment: payment, paymentTypeList:paymentTypeList]
            }

            redirect(action: "index")
        } catch (RuntimeException runtimeException) {
            flash.erro = runtimeException.getMessage()
            redirect(action: "index")
        } catch (Exception exception) {
            flash.erro = "Erro ao criar cobrança. Por favor, contate o suporte"
            redirect(action: "index")
        }
        
    }

    def list() {
        try {
            List<Payment> paymentList = PaymentRepository.query([customerId: getCurrentCustomerId()]).list()
            return [paymentList: paymentList]
        } catch (RuntimeException runtimeException) {
            flash.erro = runtimeException.getMessage()
            redirect(action: "index")
        } catch (Exception exception) {
            flash.erro = "Erro ao buscar cobranças. Por favor, contate o suporte"
            redirect(action: "index")
        }
    }

    def fetchAllCustomerAndPayerPayment() {
        try {
            List<Payment> paymentList = PaymentRepository
                .query([customerId: getCurrentCustomerId(), payerId: params.payerId.toLong()]).list()
            return [paymentList: paymentList]
        } catch (RuntimeException runtimeException) {
            flash.erro = runtimeException.getMessage()
            redirect(action: "index")
        } catch (Exception exception) {
            flash.erro = "Erro ao buscar cobranças. Por favor, contate o suporte"
            redirect(action: "index")
        }
    }

    def delete() {
        try {
            paymentService.delete(params.id.toLong(), getCurrentCustomerId())
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
