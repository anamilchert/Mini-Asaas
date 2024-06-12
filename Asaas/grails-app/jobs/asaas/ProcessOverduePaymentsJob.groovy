package asaas

import asaas.Payment
import asaas.PaymentService

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class ProcessOverduePaymentsJob {

    PaymentService paymentService

    static triggers = {
        cron name: "expiredPaymentTrigger",  cronExpression: "0 0/1 16 * * ?"
    }

    def execute() {
        try {
            List<Payment> paymentList = paymentService.processOverduePaymentsJob()
        } catch (Exception exception) {
            logger.error("Ocorreu um erro durante o processamento dos pagamentos vencidos: ${exception.message}", exception)
        }
    }
}
