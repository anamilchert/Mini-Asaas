package asaas

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
    }catch (Exception e){
      println e
      return "Error when creating payer"
    }
  }

  def show(Long id){
    Payer payer = Payer.get(id)
    if(payer){
      return [payer: payer]
    }
    render "Página não encontrada"
  }
}