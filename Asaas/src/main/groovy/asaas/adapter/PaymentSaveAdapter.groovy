package asaas.adapter

import asaas.utils.CurrencyUtils
import asaas.utils.CustomDateUtils
import asaas.PaymentStatus
import asaas.PaymentType

class PaymentSaveAdapter {
  Integer value

  Date maturityDate

  PaymentType method

  PaymentStatus status

  Long customerId

  Long payerId

  PaymentSaveAdapter(Map params) {
    this.value = CurrencyUtils.fromStringToInteger(params.value)
    this.maturityDate = CustomDateUtils.setTimeToEndOfDay(params.maturityDate)
    this.method = PaymentType.convert(params.method)
    this.status = PaymentStatus.WAITING
    this.customerId = params.customerId.toLong()
    this.payerId = params.payerId.toLong()
  }
}