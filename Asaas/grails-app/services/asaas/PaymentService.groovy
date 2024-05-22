package asaas

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import java.text.SimpleDateFormat
import asaas.Customer
import asaas.Payer
import asaas.Payment
import asaas.PaymentType
import asaas.PaymentStatus
import asaas.utils.CustomDateUtils

@Transactional
class PaymentService {
  
  public Payment save(Map params, Long customerId, Long payerId){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd")

    Customer customer = Customer.load(customerId)

    Payer payer = Payer.load(payerId)

    Payment payment = new Payment()
    payment.value = params.value.replaceAll("[.,]", "").toInteger()
    payment.maturityDate = sdf.parse(params.maturityDate)
    payment.method = PaymentType.convert(params.paymentType)
    payment.status = PaymentStatus.WAITING
    payment.customer = customer
    payment.payer = payer
    
    payment.save(failOnError: true)

    return payment
  }

}