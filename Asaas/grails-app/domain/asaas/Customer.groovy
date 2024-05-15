    package asaas

    import asaas.utils.BaseEntity
    import asaas.Payer

    class Customer extends BaseEntity {

        String name

        String email

        String cpfCnpj
        
        PersonType personType

        static hasMany = [payers: Payer]

        static constraints = {
            name blank: false
            email blank: false, unique: true 
            cpfCnpj size: 11..14, unique: true, blank: false
            personType blank: false
        }

        static namedQueries = {
            query { search ->
            if (!Boolean.valueOf(search.includeDeleted)) {
                eq('deleted', false)
            }
            if (search.containsKey('id')) {
                eq('id', search.id)
            }
            }
        }

    }