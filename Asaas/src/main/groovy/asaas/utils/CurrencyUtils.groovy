package asaas.utils

class CurrencyUtils {

    public static BigDecimal fromStringToBigDecimal(String currency) {
        try {
            String valueWithoutThousands = currency.replaceAll('\\.', '').replace(',', '.')
            return new BigDecimal(valueWithoutThousands)
        } catch (Exception e) {
            return null
        }
    }

    public static String replaceDotToComma(BigDecimal currency) {
        String replacedCurrency = currency.toString().replace(".", ",")
        return replacedCurrency
    }
}