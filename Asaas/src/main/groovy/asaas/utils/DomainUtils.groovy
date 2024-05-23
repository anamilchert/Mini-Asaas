package asaas.utils

class DomainUtils {

    public static Object addError(Object entity, String message) {
        entity.errors.reject("", null, message)

        return entity
    }

    public static Object addFieldError(Object entity, String field, String message) {
        entity.errors.rejectValue(field, message)

        return entity
    }
}