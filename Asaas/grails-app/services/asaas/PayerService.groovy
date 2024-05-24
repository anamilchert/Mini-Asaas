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

    public List<Payer> list(Long customerId) {
        List<Payer> payerList = Payer.query(customerId: customerId).list() as List<Payer>
        return payerList
    }

    private Payer validationSave(PayerSaveAdapter payerSaveAdapter) {
        Payer payer = new Payer()

        if (!payerSaveAdapter.name) {
            DomainUtils.addError(payer, "Nome é obrigatório")
        }

        if (!payerSaveAdapter.email) {
            DomainUtils.addError(payer, "Email é obrigatório")
        }

        if (!payerSaveAdapter.phone) {
            DomainUtils.addError(payer, "Phone é obrigatório")
        }

        if (!payerSaveAdapter.cpfCnpj) {
            DomainUtils.addError(payer, "CPF/CNPJ é obrigatório")
        }

        if (!payerSaveAdapter.personType) {
            DomainUtils.addError(payer, "Tipo de pessoa inválido")
        }

        if (!payerSaveAdapter.customerId) {
            DomainUtils.addError(payer, "Id do cliente é obrigatório")
        }

        if (!payerSaveAdapter.street || !payerSaveAdapter.number || 
            !payerSaveAdapter.neighborhood || !payerSaveAdapter.city || !payerSaveAdapter.state || 
            !payerSaveAdapter.CEP) 
        {
            DomainUtils.addError(payer, "Endereço incompleto")
        }

        return payer
    }
}