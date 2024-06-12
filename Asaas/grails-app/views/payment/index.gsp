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
        <atlas-form action="${createLink(customer: "payer", action: "save", params:[customerId: customerId])}">
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
                    <atlas-input
                        name="customerId"
                        required="true"
                        value="${customerId}"
                        hidden
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-select label="Pagador" placeholder="Selecione uma opção" required>
                            <g:each var="payer" in="${ payerList }">
                                <atlas-option name="payerId" label="${payer.name}" value="${payer.id}"></atlas-option>
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
                        <atlas-masked-input
                            label="Telefone"
                            name="phone"
                            mask-alias="phone"
                            label="Input de Celular"
                            required="true"
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-element-group required-fields="1" gap="4" inline>
                            <g:each var="personType" in="${ personTypeList}">
                                <atlas-radio 
                                    value="${personType}" 
                                    name="personType" 
                                    required="true"
                                >${personType.getLabel()}</atlas-radio>
                            </g:each>
                        </atlas-element-group>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="CPF/CNPJ"
                            name="cpfCnpj"
                            mask-alias="cpf-cnpj"
                            label="Input de CPF/CNPJ"
                            required="true"
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <atlas-button submit description="Salvar"></atlas-button>
        </atlas-form>
    </atlas-panel>
    <%-- <g:if test="${ flash.error }">
       <p>${flash.error}</p>
    </g:if>
  <form action="${createLink(controller:"payment", action:"save")}" method="post">
    <div>
      <label for="customerId">Id: 
        <input type="text" name="customerId" value="${customerId}">
      </label>
    </div>
    <g:select name="payerId" from="${payerList}" optionKey="id" optionValue="name"
      noSelection="['':'Selecione um pagador']" 
    />
    <div>
      <label for="value">
        Valor: 
        <input type="text" name="value" placeholder="100,00">
      </label>
    </div>

    <div>
      <label for="dueDate">
        Data de vencimento: 
        <input type="date" name="dueDate" id="">
      </label>
    </div>

    <div>
      <label for="type">
        Forma de pagamento:
        <select name="type" id="">
        <g:each var="type" in="${ paymentTypes }">
          <option value="${type}">${type.getLabel()}</option>
        </g:each>
        </select>
      </label>
    </div>

    <button type="submit">Registrar</button>
  </form> --%>
</body>
</html>