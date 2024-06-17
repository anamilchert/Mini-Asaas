package asaas

import java.text.NumberFormat
import java.util.Locale

class CurrencyTagLib {
    
    static final namespace = "currency"
    
     def formatCurrecy = { attrs, body ->
        def currency = attrs.currency
        def formattedCurrency = NumberFormat.getCurrencyInstance(new Locale('pt', 'BR')).format(currency)
        out << formattedCurrency
    }
}