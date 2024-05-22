package asaas

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import java.text.SimpleDateFormat
import asaas.Customer
import asaas.Payer
import asaas.Payment
import asaas.PaymentType
import asaas.PaymentStatus
import asaas.adapter.PaymentSaveAdapter

@Transactional
class PaymentService {
  
  public Payment save(PaymentSaveAdapter data){
    
    Customer customer = Customer.load(data.customerId)

    Payer payer = Payer.load(data.payerId)

    Payment payment = new Payment()
    payment.value = data.value
    payment.maturityDate = data.maturityDate
    payment.method = data.method
    payment.status = data.status
    payment.customer = customer
    payment.payer = payer
    
    payment.save(failOnError: true)

    return payment
  }

}