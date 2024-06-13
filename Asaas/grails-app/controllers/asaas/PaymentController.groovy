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
        Long customerId = getCurrentCustomerId()
        List<Payer> payerList = PayerRepository.query([customerId: customerId]).list()
        List<PaymentType> paymentTypes = PaymentType.values()
        return [payerList: payerList, paymentTypes: paymentTypes]
    }

    def save() {
        try {
            Long customerId = getCurrentCustomerId()
            PaymentAdapter paymentAdapter = new PaymentAdapter(params, customerId)
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
            Long customerId = getCurrentCustomerId()
            PaymentAdapter paymentAdapter = new PaymentAdapter(params, customerId)
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
        List<Payment> paymentList = PaymentRepository.query([customerId: getCurrentCustomerId()]).list()
        return [paymentList: paymentList]
    }

    def fetchAllCustomerAndPayerPayment() {
        List<Payment> paymentList = PaymentRepository
            .query([customerId: getCurrentCustomerId(), payerId: params.payerId.toLong()]).list()
        return [paymentList: paymentList]
    }
}
