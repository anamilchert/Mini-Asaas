package asaas 

import asaas.PaymentService
import asaas.Payment

class PaymentController {

  def paymentService

  def index() {
    
  }

  def save() {
    try{
      Long customerId = 1
      Long payerId = 1
      Payment payment = paymentService.save(params, customerId, payerId)
      redirect(action:"show", id:payment.id)

    }catch (Exception e){
      println e
      return "Error when creating payment"
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