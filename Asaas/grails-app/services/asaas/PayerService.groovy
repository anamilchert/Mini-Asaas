package asaas

import asaas.adapter.PayerAdapter
import asaas.Address
import asaas.Customer
import asaas.Payer
import asaas.PersonType
import asaas.State
import asaas.repositories.PayerRepository
import asaas.utils.CpfCnpjUtils
import asaas.utils.DomainUtils

import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@GrailsCompileStatic
@Transactional
class PayerService {

    public Payer save(PayerAdapter payerAdapter) {
        Payer validatedPayer = validate(payerAdapter, false)

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao criar um pagador", validatedPayer.errors)

        Address address = new Address()
        address.street = payerAdapter.street
        address.number = payerAdapter.number
        address.province = payerAdapter.province
        address.city = payerAdapter.city
        address.state = payerAdapter.state
        address.complement = payerAdapter.complement
        address.zipCode = payerAdapter.zipCode

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
        Payer payer = PayerRepository.query([customerId: payerAdapter.customerId, id: payerId]).get() as Payer
        if(!payer) throw new RuntimeException("Pagador não encontrado")

        Payer validatedPayer = validate(payerAdapter, true)
        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao atualizar um pagador", validatedPayer.errors)

        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.phone = payerAdapter.phone
        payer.address.street = payerAdapter.street
        payer.address.number = payerAdapter.number
        payer.address.province = payerAdapter.province
        payer.address.city = payerAdapter.city
        payer.address.state = payerAdapter.state
        payer.address.complement = payerAdapter.complement
        payer.address.zipCode = payerAdapter.zipCode

        payer.save(failOnError: true)

        return payer
    }

    public void delete(Long payerId, Long customerId) {
        Payer payer = PayerRepository.query([customerId: customerId, id: payerId]).get() as Payer
        if (!payer) throw new RuntimeException("Pagador não encontrado")

        payer.deleted = true
        payer.save(failOnError: true)
    }

    public void restore(Long payerId) {
        Payer payer = PayerRepository.query([includeDeleted: true, id: payerId]).get() as Payer

        if (!payer) throw new RuntimeException("Pagador não encontrado")

        if (!payer.deleted) throw new RuntimeException("Só é possível restaurar pagadores deletados")

        payer.deleted = false
        payer.save(failOnError: true)
    }

    private Payer validate(PayerAdapter payerAdapter, Boolean isUpdate) {
        Payer payer = new Payer()

        if (!isUpdate && !payerAdapter.customerId) throw new RuntimeException("Customer id não informado")

        if (!isUpdate && !payerAdapter.cpfCnpj) DomainUtils.addError(payer, "CPF/CNPJ é obrigatório")

        if (!isUpdate && !CpfCnpjUtils.isValidCpfCnpj(payerAdapter.cpfCnpj)) DomainUtils.addError(payer, "CPF/CNPJ é inválido")

        if (!isUpdate && !payerAdapter.personType) DomainUtils.addError(payer, "Informe um tipo de pessoa")

        if (!isUpdate && !(payerAdapter.personType in PersonType.values())) DomainUtils.addError(payer, "Tipo de pessoa inválido")

        if (!payerAdapter.name) DomainUtils.addError(payer, "Nome é obrigatório")

        if (!payerAdapter.email) DomainUtils.addError(payer, "Email é obrigatório")

        if (hasValidAddress(payerAdapter)) DomainUtils.addError(payer, "Endereço incompleto")

         if (!State.validate(payerAdapter.state)) {
            payer.errors.rejectValue("state", "invalid")
        }

        return payer
    }


    private static Boolean hasValidAddress(PayerAdapter payerAdapter) {
        if (!payerAdapter.street) return false

        if (!payerAdapter.number) return false

        if (!payerAdapter.province) return false

        if (!payerAdapter.city) return false

        if (!payerAdapter.state) return false

        if (!payerAdapter.zipCode) return false

        return false
    }
}