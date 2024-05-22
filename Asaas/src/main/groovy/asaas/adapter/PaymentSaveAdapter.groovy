package asaas.adapter

import asaas.utils.CustomDateUtils
import asaas.utils.CurrencyUtils
import asaas.PaymentStatus
import asaas.PaymentType

class PaymentSaveAdapter {
  Integer value

  Date maturityDate

  PaymentType method

  PaymentStatus status

  Long customerId

  Long payerId

  PaymentSaveAdapter(Map params, Long customerId, Long payerId){
    this.value = CurrencyUtils.fromStringToInteger(params.value)
    this.maturityDate = CustomDateUtils.setTimeToEndOfDay(params.maturityDate)
    this.method = PaymentType.convert(params.method)
    this.status = PaymentStatus.WAITING
    this.customerId = customerId
    this.payerId = payerId
  }
}