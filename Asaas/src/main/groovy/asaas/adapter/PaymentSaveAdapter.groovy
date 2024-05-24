package asaas.adapter

import asaas.utils.CurrencyUtils
import asaas.utils.CustomDateUtils
import asaas.PaymentStatus
import asaas.PaymentType

class PaymentSaveAdapter {

  Integer value

  Date dueDate

  PaymentType type

  PaymentStatus status

  Long customerId

  Long payerId

  public PaymentSaveAdapter(Map params) {
    this.value = CurrencyUtils.fromStringToInteger(params.value)
    this.dueDate = CustomDateUtils.setTimeToEndOfDay(params.dueDate)
    this.type = PaymentType.convert(params.type)
    this.status = PaymentStatus.PENDING
    this.customerId = params.customerId.toLong()
    this.payerId = params.payerId.toLong()
  }
}