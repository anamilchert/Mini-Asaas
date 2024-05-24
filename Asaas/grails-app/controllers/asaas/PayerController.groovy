package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.Customer
import asaas.Payer
import asaas.PayerService

class PayerController {

    def payerService
  
    def index() {
    // Temporario, pois não temos ainda o forma de pegar um customer
        try {
            List<Customer> customers = Customer.list()
            [customers: customers]
            
        } catch (Exception e) {
            println e
        }
    }

    def save() {
        try {
            PayerSaveAdapter payerSaveAdapter = new PayerSaveAdapter(params)
            Payer payer = payerService.save(payerSaveAdapter)
            redirect(action:"show", id:payer.id)

        } catch (Exception e) {
            return "Error when creating payer"
        }
    }

    def show(Long id) {
        Payer payer = Payer.read(id)

        if (payer) {
            return [payer: payer]
        }

        render "Pagador não encontrado"
    }
}