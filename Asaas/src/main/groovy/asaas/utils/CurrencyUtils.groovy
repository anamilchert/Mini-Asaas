package asaas.utils

class CurrencyUtils {

  public static Integer fromStringToInteger(String currency){
    try{
      return currency.replaceAll("[.,]","").toInteger()
    } catch(Exception e){
      return null
    }
  }
}