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
    Payment validatedPayment = validateSave(data)

    if (validatedPayment.hasErrors()){
      throw new ValidationException("Error ao criar uma cobrança", validatedPayment.errors)
    }

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

  private Payment validateSave(PaymentSaveAdapter data){
    Payment payment = new Payment()

    if (!data.value) {
      payment.errors.reject("value", null, "Informe um valor válido")
    }

    if (!data.maturityDate) {
      payment.errors.reject("maturityDate", null, "Informe uma data válida")
    } else if (data.maturityDate < new Date()){
      payment.errors.reject("maturityDate", null, "Informe uma data superior à atual")
    }

    if (!data.method) {
      payment.errors.reject("method", null, "Informe uma forma de pagamento válida")
    }

    if (!data.status) {
      payment.errors.reject("status", null, "Informe um status de pagamento válido")
    }

    if (!data.customerId) {
      payment.errors.reject("customer", null, "Informe um cliente válido")
    }

    if (!data.payerId) {
      payment.errors.reject("payer", null, "Informe um pagador válido")
    }

    return payment

  }

}