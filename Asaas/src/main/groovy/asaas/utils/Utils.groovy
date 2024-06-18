package asaas.utils

class Utils {
    
    public static String removeNonNumerics(String text) {
        return text.replaceAll(/\D/, "")
    }
}