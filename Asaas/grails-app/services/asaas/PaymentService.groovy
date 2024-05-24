package asaas

import asaas.adapter.PaymentSaveAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payment
import asaas.PaymentStatus
import asaas.PaymentType
import asaas.utils.DomainUtils

import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import java.text.SimpleDateFormat

@GrailsCompileStatic
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
            DomainUtils.addError(payment, "Informe um valor válido")
        }

        if (!paymentSaveAdapter.dueDate) {
            DomainUtils.addError(payment, "Informe um valor válido")
        } else if (paymentSaveAdapter.dueDate < currentDate) {
            payment.errors.reject("dueDate", null, "Informe uma data superior à atual")
        }

        if (!paymentSaveAdapter.type) {
            DomainUtils.addError(payment, "Tipo de pagamento inválido")
        }

        if (!paymentSaveAdapter.status) {
            DomainUtils.addError(payment, "Status de pagamento inválido")
        }

        if (!paymentSaveAdapter.customerId) {
            DomainUtils.addError(payment, "Informe um cliente válido")
        }

        if (!paymentSaveAdapter.payerId) {
            DomainUtils.addError(payment, "Informe um pagador válido")
        }

        return payment
    }
}