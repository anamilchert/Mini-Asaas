package asaas.utils

class CurrencyUtils {

    public static BigDecimal fromStringToBigDecimal(String currency) {
        try {
            return currency.replaceAll("[.,]","").toBigDecimal()

        } catch (Exception e) {
            return null
        }
    }
}