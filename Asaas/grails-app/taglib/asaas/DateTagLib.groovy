package asaas

class DateTagLib {
    static final namespace = "date"

    def formatDate = { attrs, body ->
        def inputDate = attrs.date
        def formattedDate = new java.text.SimpleDateFormat('dd/MM/yyyy').format(inputDate)
        out << formattedDate
    }
}
