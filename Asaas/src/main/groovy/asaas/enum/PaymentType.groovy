package asaas
import grails.util.Holders

enum PaymentType {
    BOLETO,
    CREDIT_CARD,
    DEBIT_CARD,
    PIX

  public static PaymentType convert(String paymentType) {
       try {
           if (paymentType instanceof String) paymentType = paymentType.toUpperCase()
           return paymentType as PaymentType
       } catch(Exception e) {
           return null
       }
   }

  public String getLabel() {
        return Holders.applicationContext.getBean("messageSource").getMessage("PaymentType.${this}.label", null, "", new Locale("pt", "BR"))
    } 
}