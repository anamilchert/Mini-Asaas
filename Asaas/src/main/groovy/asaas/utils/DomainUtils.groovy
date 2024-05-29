package asaas.utils

import asaas.adapter.AddressAdapter

class DomainUtils {

    public static Object addError(Object entity, String message) {
        entity.errors.reject("", null, message)

        return entity
    }
}