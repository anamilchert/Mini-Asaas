package asaas

import asaas.adapter.PayerSaveAdapter
import asaas.Address
import asaas.Customer
import asaas.Payer
import asaas.PersonType
import asaas.utils.DomainUtils

import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@GrailsCompileStatic
@Transactional
class PayerService {

    public Payer save(PayerSaveAdapter payerSaveAdapter) {
        Payer validatedPayer = validateSave(payerSaveAdapter)
        
        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao criar um pagador", validatedPayer.errors)

        Address address = new Address()
        address.street = payerSaveAdapter.street
        address.number = payerSaveAdapter.number
        address.province = payerSaveAdapter.province
        address.city = payerSaveAdapter.city
        address.state = payerSaveAdapter.state
        address.complement = payerSaveAdapter.complement
        address.zipCode = payerSaveAdapter.zipCode

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

    private Payer validateSave(PayerSaveAdapter payerSaveAdapter) {
        Payer payer = new Payer()

        if (!payerSaveAdapter.customerId) throw new RuntimeException("Customer id não informado")

        if (!payerSaveAdapter.name) DomainUtils.addError(payer, "Nome é obrigatório")

        if (!payerSaveAdapter.email) DomainUtils.addError(payer, "Email é obrigatório")

        if (!payerSaveAdapter.phone) DomainUtils.addError(payer, "Phone é obrigatório")

        if (!payerSaveAdapter.cpfCnpj) DomainUtils.addError(payer, "CPF/CNPJ é obrigatório")

        if (!payerSaveAdapter.personType) DomainUtils.addError(payer, "Informe um tipo de pessoa")

        if (!(payerSaveAdapter.personType in PersonType.values())) DomainUtils.addError(payer, "Tipo de pessoa inválido")

        if (hasValidAddress(payerSaveAdapter)) DomainUtils.addError(payer, "Endereço incompleto")

        return payer
    }

    private static Boolean hasValidAddress(PayerSaveAdapter payerSaveAdapter) {
        if (!payerSaveAdapter.street) return true

        if (!payerSaveAdapter.number) return true

        if (!payerSaveAdapter.province) return true

        if (!payerSaveAdapter.city) return true

        if (!payerSaveAdapter.state) return true

        if (!payerSaveAdapter.zipCode) return true

        return false
    }
}