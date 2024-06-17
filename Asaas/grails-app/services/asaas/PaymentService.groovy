package asaas

import asaas.adapter.PaymentAdapter
import asaas.Customer
import asaas.Payer
import asaas.Payment
import asaas.PaymentStatus
import asaas.PaymentType
import asaas.EmailService
import asaas.repositories.PaymentRepository
import asaas.utils.DomainUtils

import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import java.text.SimpleDateFormat
import org.springframework.transaction.TransactionStatus

@GrailsCompileStatic
@Transactional
class PaymentService {

    EmailService emailService

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

        emailService.sendCreatePaymentEmailToPayer(payment.payer, payment)
        emailService.sendCreatePaymentEmailToCustomer(payment.customer, payment)

        return payment
    }

    public Payment update(PaymentAdapter paymentAdapter, Long paymentId) {
        Payment payment = PaymentRepository.query([customerId: paymentAdapter.customerId, id: paymentId]).get() as Payment

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

        emailService.sendStatusChangeEmailToPayer(payment.payer, payment)
        emailService.sendStatusChangeEmailToCustomer(payment.customer, payment)

        return payment
    }

    public Payment confirmReceivedInCash(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.query([customerId: customerId, id: paymentId]).get() as Payment

        if (!payment) {
            throw new RuntimeException("Cobrança não encontrada")
        }

        if (payment.status != PaymentStatus.PENDING) {
            throw new RuntimeException("Somente cobranças aguardando pagamento podem ser recebidas em dinheiro")
        }

        payment.status = PaymentStatus.RECEIVED_IN_CASH
        payment.save(failOnError: true)

        emailService.sendStatusChangeEmailToPayer(payment.payer, payment)
        emailService.sendStatusChangeEmailToCustomer(payment.customer, payment)

        return payment
    }

    public void delete(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.query([customerId: customerId, id: paymentId]).get() as Payment
        
        if (!payment) {
            throw new RuntimeException("Pagamento não encontrado")
        }
        
        if (payment.status != PaymentStatus.PENDING) {
            throw new RuntimeException("Só é possível remover cobranças pendentes")
        }

        payment.deleted = true

        payment.save(failOnError: true)
    }

    public void restore(Long paymentId) {
        Payment payment = PaymentRepository.query([includeDeleted: true, id: paymentId]).get()

        if (!payment) {
            throw new RuntimeException("Pagamento não encontrado")
        }

        if (!payment.deleted) {
            throw new RuntimeException("Só é possível restaurar cobranças deletadas")
        }

        payment.deleted = false
        
        payment.save(failOnError: true)
    }   

    public void processOverduePayments() {
        Date today = new Date()
        List<Long> paymentIdList = PaymentRepository.query([
            ignoreCustomer: true,
            status: PaymentStatus.PENDING,
            "dueDate[le]": today
        ]).property("id").list() as List<Long>
        
        for (Long id : paymentIdList) {
            Payment.withNewTransaction { TransactionStatus status ->
                try {
                    Payment payment = Payment.get(id)
                    payment.status = PaymentStatus.OVERDUE
                    payment.save(failOnError: true)

                    emailService.sendStatusChangeEmailToPayer(payment.payer, payment)
                    emailService.sendStatusChangeEmailToCustomer(payment.customer, payment)
                } catch (Exception exception) {
                    status.setRollbackOnly()
                }
            }
        }
    }

    private Payment validate(PaymentAdapter paymentAdapter, Boolean isUpdate) {
        Payment payment = new Payment()
        
        Date currentDate = new Date()

        if (!paymentAdapter.value) DomainUtils.addError(payment, "Informe um valor válido")

        if (!paymentAdapter.dueDate) DomainUtils.addError(payment, "Informe um valor válido")
        else if (paymentAdapter.dueDate < currentDate) DomainUtils.addError(payment, "Informe uma data superior à atual")
        
        if (!paymentAdapter.type) DomainUtils.addError(payment, "Tipo de pagamento inválido")

        if (!isUpdate && !paymentAdapter.status) DomainUtils.addError(payment, "Status de pagamento inválido")

        if (!isUpdate && !paymentAdapter.customerId) DomainUtils.addError(payment, "Informe um cliente válido")

        if (!isUpdate && !paymentAdapter.payerId) DomainUtils.addError(payment, "Informe um pagador válido")

        return payment
    }
}