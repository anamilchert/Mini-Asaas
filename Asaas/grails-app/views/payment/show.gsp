<%@ page import="asaas.utils.CurrencyUtils" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="external"/>
    <title>Mostrar Pagamento</title>
</head>
<body>
    <g:if test="${ flash.message }">
        <atlas-alert message="${flash.message}" type="success"></atlas-alert>
    </g:if>
    <g:if test="${ flash.error }">
        <atlas-alert message="${flash.error}" type="error"></atlas-alert>
    </g:if>
    <atlas-panel>
         <atlas-layout gap="2" justify="start">
            <atlas-button 
                type="outlined" 
                size="sm" 
                theme="secondary" 
                description="Voltar"
                href="${createLink(controller: "payment", action: "index", params: [customerId: payment.customer.id])}"
                icon="arrow-left"
                pill
            >
            </atlas-button>
        </atlas-layout>
        <atlas-form action="${createLink(customer: "payment", action: "update", id: payment.id)}">
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-text
                            size="lg"
                            bold=""
                        >
                            Atualizar cobrança de ${payment.payer.name}
                        </atlas-text>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-money 
                            label="Valor"
                            name="value"
                            value="${CurrencyUtils.replaceDotToComma(payment.value)}"
                            required
                        >
                        </atlas-money>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input 
                            label="Status"
                            name="status"
                            value="${payment.status.getLabel()}"
                            required
                            disabled
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-select 
                            name="type" 
                            label="Método de pagamento" 
                            placeholder="Selecione uma método de pagamento" 
                            value="${payment.type}"
                            required
                        >
                            <g:each var="type" in="${ paymentTypeList }">
                                <atlas-option label="${type.getLabel()}" value="${type}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <div>
                            <atlas-datepicker 
                                name="dueDate" 
                                label="Data de vencimento"
                                value="${date.formatDate(date: payment.dueDate)}"
                                prevent-past-date 
                                required 
                            >
                            </atlas-datepicker>
                        </div>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            name="payerId"
                            required="true"
                            value="${payment.payer.id}"
                            disabled
                            hidden
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <g:if test="${ payment.status.isPending() && !payment.deleted}">
                <atlas-layout gap="2" inline="" wrap="">
                    <atlas-button submit description="Salvar"></atlas-button>
                    <atlas-button 
                        type="outlined" 
                        size="md" 
                        theme="danger" 
                        description="Cancelar"
                        href="${createLink(controller: "payment", action: "index")}"
                    >
                    </atlas-button>
                </atlas-layout>
            </g:if>
        </atlas-form>
    </atlas-panel>

    <g:if test="${ payment.status.isPending() && !payment.deleted}">
        <atlas-panel header="Recebemento em dinheiro">
            <atlas-layout>
                <atlas-button 
                    type="outlined" 
                    size="md" 
                    theme="success" 
                    description="Confirmar Pagamento"
                    href="${createLink(controller: "payment", action: "confirmReceivedInCash", params: [id: payment.id])}"
                >
                </atlas-button>
            </atlas-layout>
        </atlas-panel>
    </g:if>

    <g:if test="${ payment.status.isPending() && payment.deleted}">
        <atlas-panel header="Restaurar cobrança">
            <atlas-layout>
                <atlas-button 
                    type="outlined" 
                    size="md" 
                    theme="danger" 
                    description="Restaurar"
                    href="${createLink(controller: "payment", action: "restore", params: [id: payment.id])}"
                >
                </atlas-button>
            </atlas-layout>
        </atlas-panel>
    </g:if>
    
</body>
</html>