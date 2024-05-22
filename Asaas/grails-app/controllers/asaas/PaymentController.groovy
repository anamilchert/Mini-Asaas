package asaas 

import grails.validation.ValidationException
import asaas.PaymentService
import asaas.PayerService
import asaas.adapter.PaymentSaveAdapter
import asaas.Payment
import asaas.Payer

class PaymentController {

  def paymentService

  def payerService

  def index() {
    List<Payer> payerList = payerService.list(params.customerId.toLong())
    return [payerList: payerList, customerId:params.customerId]
  }

  def save() {
    try{
      PaymentSaveAdapter paymentSaveAdapter = new PaymentSaveAdapter(params)
      Payment payment = paymentService.save(paymentSaveAdapter)
      redirect(action:"show", id:payment.id)

    } catch (ValidationException e){
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