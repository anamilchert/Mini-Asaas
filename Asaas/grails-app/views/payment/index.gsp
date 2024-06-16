<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="external"/>
    <title>Registrar pagamento</title>
</head>
<body>
    <atlas-panel>
        <g:if test="${ flash.message }">
            <atlas-alert message="${flash.message}" type="success"></atlas-alert>
        </g:if>
        <g:if test="${ flash.error }">
            <atlas-alert message="${flash.error}" type="error"></atlas-alert>
        </g:if>
        <atlas-form action="${createLink(controller:"payment", action:"save")}">
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-text
                            size="lg"
                            bold=""
                        >
                            Criar cobrança
                        </atlas-text>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-select name="payerId" label="Pagador" placeholder="Selecione um pagador" required>
                            <g:each var="payer" in="${ payerList }">
                                <atlas-option value-key label="${payer.name}" value="${payer.id}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-money 
                            label="Valor"
                            name="value"
                            required
                        >
                        </atlas-money>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-select name="type" label="Método de pagamento" placeholder="Selecione uma método de pagamento" required>
                            <g:each var="type" in="${ paymentTypeList }">
                                <atlas-option label="${type.getLabel()}" value="${type}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <div>
                            <atlas-datepicker name="dueDate" label="Data de vencimento"  prevent-past-date required></atlas-datepicker>
                        </div>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <atlas-button submit description="Salvar"></atlas-button>
        </atlas-form>
    </atlas-panel>
</body>
</html>