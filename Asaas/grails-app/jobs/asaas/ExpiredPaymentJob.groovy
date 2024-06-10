package asaas

import asaas.Payment
import asaas.PaymentService

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class ExpiredPaymentJob {

    PaymentService paymentService

    static triggers = {
        cron name:   "expiredPaymentTrigger",  cronExpression: "0 0 0 * * ?"
    }

    def execute() {
        List<Payment> paymentList = paymentService.expiredPayment()
    }
}
