package asaas

import asaas.utils.BaseEntity

class Customer extends BaseEntity {

    String name
    String email
    String cpfCnpj
    PersonType personType

    static constraints = {
        name blank: false
        email email: true
        cpfCnpj size: 11..14, blank: false
        personType blank: false
    }
}