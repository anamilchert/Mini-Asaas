package asaas.utils

class CurrencyUtils {

  public static Integer fromStringToInteger(String currency){
    return currency.replaceAll("[.,]","").toInteger()
  }
}