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
  
  public Payment save(PaymentSaveAdapter paymentSaveAdapter) {
    Payment validatedPayment = validateSave(paymentSaveAdapter)

    if (validatedPayment.hasErrors()) throw new ValidationException("Error ao criar uma cobrança", validatedPayment.errors)

    Payment payment = new Payment()
    payment.value = paymentSaveAdapter.value
    payment.dueDate = paymentSaveAdapter.dueDate
    payment.type = paymentSaveAdapter.type
    payment.status = paymentSaveAdapter.status
    payment.customer = Customer.load(paymentSaveAdapter.customerId)
    payment.payer = Payer.load(paymentSaveAdapter.payerId)
    
    payment.save(failOnError: true)

    return payment
  }

  private Payment validateSave(PaymentSaveAdapter paymentSaveAdapter) {
    Payment payment = new Payment()
    Date currentDate = new Date()

    if (!paymentSaveAdapter.value) {
      payment.errors.reject("value", null, "Informe um valor válido")
    }

    if (!paymentSaveAdapter.dueDate) {
      payment.errors.reject("dueDate", null, "Informe uma data válida")
    } else if (paymentSaveAdapter.dueDate < currentDate) {
      payment.errors.reject("dueDate", null, "Informe uma data superior à atual")
    }

    if (!paymentSaveAdapter.type) {
      payment.errors.reject("type", null, "Informe uma forma de pagamento válida")
    }

    if (!paymentSaveAdapter.status) {
      payment.errors.reject("status", null, "Informe um status de pagamento válido")
    }

    if (!paymentSaveAdapter.customerId) {
      payment.errors.reject("customer", null, "Informe um cliente válido")
    }

    if (!paymentSaveAdapter.payerId) {
      payment.errors.reject("payer", null, "Informe um pagador válido")
    }

    return payment
  }
}