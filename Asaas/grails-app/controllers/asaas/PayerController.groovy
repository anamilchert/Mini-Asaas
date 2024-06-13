package asaas

import asaas.adapter.PayerAdapter
import asaas.BaseController
import asaas.Customer
import asaas.Payer
import asaas.Payer
import asaas.PayerService
import asaas.repositories.PayerRepository

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException


@Secured('IS_AUTHENTICATED_FULLY')
class PayerController extends BaseController{

    PayerService payerService

    def index() {
    }


    def save() {
        try {
            Long customerId = getCurrentCustomerId()
            PayerAdapter payerAdapter = new PayerAdapter(params, customerId)
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
        Payer payer = PayerRepository.query([id: params.id.toLong()]).get()
        Long customerId = getCurrentCustomerId()
        
        if(!payer || getCurrentCustomerId() != payer.customer.id) redirect(uri: "/")

        return [payer: payer]
    }

    def list() {
        List<Payer> payerList = PayerRepository.query([customerId:getCurrentCustomerId()]).list()
        return [payerList: payerList]   
    }

    def update() {
        try {
            Long customerId = getCurrentCustomerId()
            PayerAdapter payerAdapter = new PayerAdapter(params, customerId)
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