package asaas

import asaas.adapter.PayerAdapter
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

    public Payer save(PayerAdapter payerAdapter) {
        Payer validatedPayer = validation(payerAdapter)
        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao criar um pagador", validatedPayer.errors)

        Address address = new Address()
        address.street = payerAdapter.street
        address.number = payerAdapter.number
        address.neighborhood = payerAdapter.neighborhood
        address.city = payerAdapter.city
        address.state = payerAdapter.state
        address.complement = payerAdapter.complement
        address.CEP = payerAdapter.CEP

        Payer payer = new Payer()
        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.cpfCnpj = payerAdapter.cpfCnpj
        payer.phone = payerAdapter.phone
        payer.personType = payerAdapter.personType
        payer.address = address
        payer.customer = Customer.load(payerAdapter.customerId)

        payer.save(failOnError: true)

        return payer
    }

    public Payer update(PayerAdapter payerAdapter, Long payerId) {
        Payer payer = Payer.get(payerId)

        if(!payer || payer.deleted) throw new RuntimeException("Pagador não encontrado")

        Payer validatedPayer = validation(payerAdapter, true)
        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao atualizar um pagador", validatedPayer.errors)

        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.phone = payerAdapter.phone
        payer.address.street = payerAdapter.street
        payer.address.number = payerAdapter.number
        payer.address.neighborhood = payerAdapter.neighborhood
        payer.address.city = payerAdapter.city
        payer.address.state = payerAdapter.state
        payer.address.complement = payerAdapter.complement
        payer.address.CEP = payerAdapter.CEP

        payer.save(failOnError: true)

        return payer
    }

    public List<Payer> list(Long customerId) {
        List<Payer> payerList = Payer.query(customerId: customerId).list() as List<Payer>
        return payerList
    }

    private Payer validation(PayerAdapter payerAdapter, Boolean update = false) {
        Payer payer = new Payer()

        if (!update && !payerAdapter.customerId) throw new RuntimeException("Customer id não informado")

        if (!update && !payerAdapter.cpfCnpj) DomainUtils.addError(payer, "CPF/CNPJ é obrigatório")

        if (!update && !payerAdapter.personType) DomainUtils.addError(payer, "Tipo de pessoa inválido")

        if (!payerAdapter.name) DomainUtils.addError(payer, "Nome é obrigatório")

        if (!payerAdapter.email) DomainUtils.addError(payer, "Email é obrigatório")

        if (!payerAdapter.phone) DomainUtils.addError(payer, "Phone é obrigatório")

        if (hasIncompleteAddress(payerAdapter)) DomainUtils.addError(payer, "Endereço incompleto")

        return payer
    }

    private Boolean hasIncompleteAddress(PayerAdapter payerAdapter) {
        if (!payerAdapter.street) return true

        if (!payerAdapter.number) return true

        if (!payerAdapter.neighborhood) return true

        if (!payerAdapter.city) return true

        if (!payerAdapter.state) return true

        if (!payerAdapter.CEP) return true

        return false
    }
}