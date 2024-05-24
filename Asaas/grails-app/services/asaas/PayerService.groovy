package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.Address
import asaas.Customer
import asaas.Payer
import asaas.PersonType

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class PayerService {

    public Payer save(PayerSaveAdapter payerSaveAdapter) {
        Payer validatedPayer = validationSave(payerSaveAdapter)
        
        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao criar um pagador", validatedPayer.errors)

        Address address = new Address()
        address.street = payerSaveAdapter.street
        address.number = payerSaveAdapter.number
        address.neighborhood = payerSaveAdapter.neighborhood
        address.city = payerSaveAdapter.city
        address.state = payerSaveAdapter.state
        address.complement = payerSaveAdapter.complement
        address.CEP = payerSaveAdapter.CEP

        Payer payer = new Payer()
        payer.name = payerSaveAdapter.name
        payer.email = payerSaveAdapter.email
        payer.cpfCnpj = payerSaveAdapter.cpfCnpj
        payer.phone = payerSaveAdapter.phone
        payer.personType = payerSaveAdapter.personType
        payer.address = address
        payer.customer = Customer.load(payerSaveAdapter.customerId)

        payer.save(failOnError: true)

        return payer
    }

    private Payer validationSave(PayerSaveAdapter payerSaveAdapter) {
        Payer payer = new Payer()

        if (!payerSaveAdapter.name) {
            payer.errors.reject("name", null, "Nome é obrigatório")
        }

        if (!payerSaveAdapter.email) {
            payer.errors.reject("email", null, "Email é obrigatório")
        }

        if (!payerSaveAdapter.phone) {
            payer.errors.reject("phone", null, "Phone é obrigatório")
        }

        if (!payerSaveAdapter.cpfCnpj) {
            payer.errors.reject("cpfCnpj", null, "CPF/CNPJ é obrigatório")
        }

        if (!payerSaveAdapter.street || !payerSaveAdapter.number || 
            !payerSaveAdapter.neighborhood || !payerSaveAdapter.city || !payerSaveAdapter.state || 
            !payerSaveAdapter.CEP) 
        {
            payer.errors.reject("address", null, "Endereço incompleto")
        }

        return payer
    }

}
