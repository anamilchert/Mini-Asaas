package asaas

import asaas.adapter.PaymentAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payment
import asaas.PaymentStatus
import asaas.PaymentType
import asaas.repositories.PaymentRepository
import asaas.utils.DomainUtils

import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import java.text.SimpleDateFormat

@GrailsCompileStatic
@Transactional
class PaymentService {
  
    public Payment save(PaymentAdapter paymentAdapter) {
        Payment validatedPayment = validate(paymentAdapter, false)
        if (validatedPayment.hasErrors()) throw new ValidationException("Error ao criar uma cobrança", validatedPayment.errors)

        Payment payment = new Payment()
        
        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate
        payment.type = paymentAdapter.type
        payment.status = paymentAdapter.status
        payment.customer = Customer.load(paymentAdapter.customerId)
        payment.payer = Payer.load(paymentAdapter.payerId)
        
        payment.save(failOnError: true)

        return payment
    }

    public Payment update(PaymentAdapter paymentAdapter) {
        public payment = PaymentRepository.query([id: id]).get() as Payment

        Payment validatedPayment = validate(paymentAdapter, true)
        if (validatedPayment.hasErrors()) throw new ValidationException("Error ao editar uma cobrança", validatedPayment.errors)

        if (!payment) {
            throw new RuntimeException("Pagador não encontrado")
        }

        if (payment.status != PaymentStatus.PENDING) {
            throw new RuntimeException("Não é possível atualizar a cobrança")
        }

        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate
        payment.type = paymentAdapter.type

        payment.save(failOnError: true)

        return payment
    }

    public Payment getPaymentById(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get() as Payment
        return payment
    }

    public List<Payment> listCustomerPayments(Long customerId) {
        List<Payment> paymentList = PaymentRepository.query([customerId: customerId]).list() as List<Payment>
        return paymentList
    }

    public List<Payment> listCustomerAndPayerPayments(Long customerId, Long payerId) {
        List<Payment> paymentList = PaymentRepository.query([customerId: customerId, payerId: payerId]).list() as List<Payment>
        return paymentList
    }

    private Payment validate(PaymentAdapter paymentAdapter, Boolean isUpdate) {
        Payment payment = new Payment()
        
        Date currentDate = new Date()

        if (!paymentAdapter.value) {
            DomainUtils.addError(payment, "Informe um valor válido")
        }

        if (!paymentAdapter.dueDate) {
            DomainUtils.addError(payment, "Informe um valor válido")
        } else if (paymentAdapter.dueDate < currentDate) {
            payment.errors.reject("dueDate", null, "Informe uma data superior à atual")
        }

        if (!paymentAdapter.type) {
            DomainUtils.addError(payment, "Tipo de pagamento inválido")
        }

        if (!update && !paymentAdapter.status) {
            DomainUtils.addError(payment, "Status de pagamento inválido")
        }

        if (!update && !paymentAdapter.customerId) {
            DomainUtils.addError(payment, "Informe um cliente válido")
        }

        if (!update && !paymentAdapter.payerId) {
            DomainUtils.addError(payment, "Informe um pagador válido")
        }

        return payment
    }
}