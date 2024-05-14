package asaas
import grails.util.Holders

enum PaymentStatus {
  WAITING,
  APPROVED,
  CANCELED,
  EXPIRED

  // final String value

  // PaymentStatus(String value){
  //   this.value = value
  // }

  public static PaymentStatus convert(String paymentStatus) {
       try {
           if (paymentStatus instanceof String) paymentStatus = paymentStatus.toUpperCase()
           return paymentStatus as PaymentStatus
       } catch(Exception e) {
           return null
       }
   }

  public String getLabel() {
      return Holders.applicationContext.getBean("messageSource").getMessage("PaymentStatus.${this}.label", null, "", new Locale("pt", "BR"))
  }
}