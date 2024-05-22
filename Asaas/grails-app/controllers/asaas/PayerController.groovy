package asaas

import grails.validation.ValidationException
import asaas.Payer
import asaas.Customer
import asaas.PayerService

class PayerController {
  
  def payerService
  
  def index(){
    // Temporario, pois não temos ainda o forma de pegar um customer
    try {
      List<Customer> customers = Customer.list()
      [customers: customers]
      
    }catch (Exception e){
      println e
    }
  }

  def save(){
    try{
      Payer payer = payerService.save(params)
      redirect(action:"show", id:payer.id)

    }catch (ValidationException e){
      String errorsMessage = e.errors.allErrors.defaultMessage.join(", ")
      flash.error = "Não foi possível salvar um pagador: $errorsMessage"
      render(view: 'show', params: params)
    }
  }

  def show(Long id){
    Payer payer = Payer.read(id)

    if (payer) {
      return [payer: payer]
    }

    render "Pagador não encontrado"
  }

  def list(){
    List<Payer> payerList = payerService.list(params.customerId.toLong())
    return [payerList: payerList]
  }

}