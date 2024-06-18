package asaas

import asaas.adapter.PayerAdapter
import asaas.BaseController
import asaas.Customer
import asaas.Payer
import asaas.Payer
import asaas.PayerService
import asaas.PersonType
import asaas.repositories.PayerRepository

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException


@Secured('IS_AUTHENTICATED_FULLY')
class PayerController extends BaseController{

    PayerService payerService

    def index() {
        List<PersonType> personTypeList = PersonType.values()
        return [personTypeList: personTypeList]
    }


    def save() {
        try {
            Long customerId = getCurrentCustomerId()
            PayerAdapter payerAdapter = new PayerAdapter(params, customerId)
            Payer payer = payerService.save(payerAdapter)
            redirect(action:"show", id:payer.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível salvar um pagador"
            redirect(action: "index", params: [customerId: params.customerId])
        } catch (RuntimeException runtimeException) {
            flash.error = "Ocorreu um erro ao tentar criar um pagador. Por favor, tente novamente"
            redirect(action: "index", params: [customerId: params.customerId])
        } catch (Exception exception) {
            flash.error = "Ocorreu um erro inesperado ao tentar criar um pagador. Por favor, tente novamente"
            redirect(action: "index", params: [customerId: params.customerId])
        }
    }

    def show() {
        try {
            Payer payer = PayerRepository.query([includeDeleted: true, customerId: getCurrentCustomerId(), id: params.id.toLong()]).get()
            if(!payer) throw new RuntimeException("Pagador não encontrado")
            return [payer: payer]
        } catch (RuntimeException runtimeException) {
            flash.message = runtimeException.getMessage()
            redirect(action: "index")
        } catch (Exception exception) {
            flash.message = "Erro ao buscar o pagador. Por favor, contate o suporte"
            redirect(action: "index")
        }
        
    }

    def list() {
        try {
            List<Payer> payerList = PayerRepository.query([includeDeleted: params.includeDeleted, customerId: getCurrentCustomerId()]).list()
            return [payerList: payerList]   
        } catch (RuntimeException runtimeException) {
            flash.message = runtimeException.getMessage()
            redirect(action: "index")
        } catch (Exception exception) {
            flash.message = "Erro ao buscar lista de pagadores. Por favor, contate o suporte"
            redirect(action: "index")
        }
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
            flash.error = "Não foi possível atualizar os dados"
            redirect(action: "show", id: params.id)
        } catch (RuntimeException runtimeException) {
            flash.error = "Não foi possível atualizar os dados. Por favor, tente novamente."
            redirect(action:"show", id:params.id)
        } catch (Exception exception) {
            flash.error = "Ocorreu um error inesperado. Por favor, tente novamente."
            redirect(action:"show", id:params.id)
        }
    }

     def delete() {
        try {
            payerService.delete(params.id.toLong(), getCurrentCustomerId())
            flash.message = "Pagador excluído com sucesso"
            redirect(action: "list", params: [params.customerId])
        } catch (Exception exception) {
            flash.message = "Não foi possível excluir pagador"
            redirect(action: "index")
        }
    }

    def restore() {
        try {
            payerService.restore(params.id.toLong(), getCurrentCustomerId())
            flash.message = "Pagador restaurado com sucesso"
        } catch (RuntimeException runtimeException) {
            flash.error = runtimeException.getMessage()
        } catch (Exception exception) {
            flash.error = "Erro ao restaurar um pagador. Por favor, contate o time de suporte"
        } finally {
            redirect(action: "list")
        }
    }
}