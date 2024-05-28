package asaas

import asaas.adapter.AddressAdapter
import asaas.adapter.PayerSaveAdapter
import asaas.adapter.PayerUpdateAdapter
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

    public Payer update(PayerUpdateAdapter payerUpdateAdapter, Long payerId) {
        Payer payer = Payer.get(payerId)

        if(!payer || payer.deleted) throw new RuntimeException("Pagador não encontrado")

        Payer validatedPayer = validationUpdate(payerUpdateAdapter)

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao atualizar um pagador", validatedPayer.errors)

        payer.name = payerUpdateAdapter.name
        payer.email = payerUpdateAdapter.email
        payer.phone = payerUpdateAdapter.phone
        payer.address.street = payerUpdateAdapter.street
        payer.address.number = payerUpdateAdapter.number
        payer.address.neighborhood = payerUpdateAdapter.neighborhood
        payer.address.city = payerUpdateAdapter.city
        payer.address.state = payerUpdateAdapter.state
        payer.address.complement = payerUpdateAdapter.complement
        payer.address.CEP = payerUpdateAdapter.CEP

        payer.save(failOnError: true)

        return payer
    }

    public List<Payer> list(Long customerId) {
        List<Payer> payerList = Payer.query(customerId: customerId).list() as List<Payer>
        return payerList
    }

    private Payer validationSave(PayerSaveAdapter payerSaveAdapter) {
        Payer payer = new Payer()

        if (!payerSaveAdapter.customerId) throw new RuntimeException("Customer id não informado")

        if (!payerSaveAdapter.name) DomainUtils.addError(payer, "Nome é obrigatório")

        if (!payerSaveAdapter.email) DomainUtils.addError(payer, "Email é obrigatório")

        if (!payerSaveAdapter.phone) DomainUtils.addError(payer, "Phone é obrigatório")

        if (!payerSaveAdapter.cpfCnpj) DomainUtils.addError(payer, "CPF/CNPJ é obrigatório")

        if (!payerSaveAdapter.personType) DomainUtils.addError(payer, "Tipo de pessoa inválido")

        if (DomainUtils.hasIncompleteAddress(payerSaveAdapter)) DomainUtils.addError(payer, "Endereço incompleto")

        return payer
    }

    private Payer validationUpdate(PayerUpdateAdapter payerUpdateAdapter) {
        Payer payer = new Payer()

        if (!payerUpdateAdapter.name) DomainUtils.addError(payer, "Nome é obrigatório")

        if (!payerUpdateAdapter.email) DomainUtils.addError(payer, "Email é obrigatório")

        if (!payerUpdateAdapter.phone) DomainUtils.addError(payer, "Phone é obrigatório")

        if (DomainUtils.hasIncompleteAddress(payerUpdateAdapter)) DomainUtils.addError(payer, "Endereço incompleto")

        return payer
    }
}