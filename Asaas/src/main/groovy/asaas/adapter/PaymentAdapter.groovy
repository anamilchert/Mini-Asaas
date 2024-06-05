package asaas.adapter

import asaas.utils.CurrencyUtils
import asaas.utils.CustomDateUtils
import asaas.PaymentStatus
import asaas.PaymentType

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class PaymentAdapter {

  BigDecimal value

  Date dueDate

  PaymentType type

  PaymentStatus status

  Long customerId

  Long payerId

  public PaymentAdapter(Map params) {
    this.value = CurrencyUtils.fromStringToInteger(params.value as String)
    this.dueDate = CustomDateUtils.setTimeToEndOfDay(params.dueDate as String)
    this.type = PaymentType.convert(params.type as String)
    this.status = params.status ? PaymentStatus.convert(params.status as String) : PaymentStatus.PENDING
    this.customerId = params.customerId as Long
    this.payerId = params.payerId as Long
  }
}