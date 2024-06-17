package asaas

import grails.gorm.transactions.Transactional
import java.text.SimpleDateFormat
import static grails.async.Promises.*

@Transactional
class EmailService {

    def mailService

    public void sendCreatePaymentEmailToPayer(Payer payer, Payment payment) {
        sendEmail(payer.email, "Criação de cobrança", getCreatePaymentEmailBody(payer.name, payment))
    }

    public void sendStatusChangeEmailToPayer(Payer payer, Payment payment) {
        sendEmail(payer.email, "Status da sua cobrança foi alterado", getStatusChangeEmailBody(payer.name, payment))
    }

    public void sendCreatePaymentEmailToCustomer(Customer customer, Payment payment) {
        sendEmail(customer.email, "Criação de cobrança", getCreatePaymentEmailBody(customer.name, payment, payer))
    }

    public void sendStatusChangeEmailToCustomer(Customer customer, Payment payment) {
        sendEmail(customer.email, "Status da sua cobrança foi alterado", getStatusChangeEmailBody(customer.name, payment))
    }

    private void sendEmail(String to, String subject, String body) {
        task {
            mailService.sendMail {
                to to
                subject subject
                body body
            }
        }
    }

    private String getCreatePaymentEmailBody(String name, Payment payment, Payer payer = null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)
        String payerInfo = payer ? " e o pagador é ${payer.name}" : ""
        return """Olá ${name},
                    Uma cobrança com valor de R\$${payment.value} foi criada${payerInfo}.
                    Por favor, se atente a data de validade: ${formattedDueDate}.
                            
                    Atenciosamente,
                    Mini Asaas."""
    }

    private String getStatusChangeEmailBody(String name, Payment payment) {
        return """Olá ${name},
                    O status da cobrança de número ${payment.id} foi alterado para: ${payment.status}.
                            
                    Atenciosamente,
                    Mini Asaas."""
    }
}