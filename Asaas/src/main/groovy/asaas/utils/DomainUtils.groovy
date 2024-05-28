package asaas.utils

import asaas.adapter.AddressAdapter

class DomainUtils {

    public static Object addError(Object entity, String message) {
        entity.errors.reject("", null, message)

        return entity
    }

    public static Boolean hasIncompleteAddress(AddressAdapter addressAdapter) {
        if (!addressAdapter.street) return true

        if (!addressAdapter.number) return true

        if (!addressAdapter.neighborhood) return true

        if (!addressAdapter.city) return true

        if (!addressAdapter.state) return true

        if (!addressAdapter.CEP) return true

        return false
    }
}