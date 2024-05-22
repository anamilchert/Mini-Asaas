package asaas 

import grails.validation.ValidationException
import asaas.PaymentService
import asaas.adapter.PaymentSaveAdapter
import asaas.Payment

class PaymentController {

  def paymentService

  def index() {
    
  }

  def save() {
    try{
      Long customerId = 1
      Long payerId = 1
      PaymentSaveAdapter paymentSaveAdapter = new PaymentSaveAdapter(params, customerId, payerId)
      Payment payment = paymentService.save(paymentSaveAdapter)
      redirect(action:"show", id:payment.id)

    }catch (ValidationException e){
      String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
      flash.error = "Não foi possível salvar uma cobrança: $errorsMessage"
      println flash.error
      render(view: 'show', params: params)
    }
  }

  def show(Long id) {
    Payment payment = Payment.read(id)

    if (payment) {
      return [payment: payment]
    }

    render "Cobrança não encontrado"
  }

}