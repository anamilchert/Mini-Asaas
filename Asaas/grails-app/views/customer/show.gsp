<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="internal"/>
    <title>Detalhes</title>
</head>
<body>
     <g:if test="${ flash.message }">
        <atlas-alert message="${flash.message}" type="success"></atlas-alert>
    </g:if>
    <g:if test="${ flash.error }">
        <atlas-alert message="${flash.error}" type="error"></atlas-alert>
    </g:if>
    <atlas-panel>
        <atlas-form action="${createLink(customer: "customer", action: "update")}">
            <atlas-text
                size="lg"   
                bold=""
            >
                Atualizar dados de ${customer.name.toUpperCase()}
            </atlas-text>
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Nome"
                            name="name"
                            value="${customer.name}"
                            required="true"
                            disabled
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
               
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="Email"
                            name="email"
                            mask-alias="email"
                            value="${customer.email}"
                            required="true"
                            disabled
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="CPF/CNPJ"
                            name="cpfCnpj"
                            mask-alias="cpf-cnpj"
                            label="Input de CPF/CNPJ"
                            value="${customer.cpfCnpj}"
                            required="true"
                            disabled
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="Tipo de pessoa"
                            name="personType"
                            value="${customer.personType}"
                            required="true"
                            disabled
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-text
                            size="lg"
                            bold=""
                        >
                            Endereço de cobrança
                        </atlas-text>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-postal-code 
                        label="CEP"
                        name="zipCode"
                        value="${customer.address.zipCode}"
                        required="true"
                        id="zipCode"
                        >
                        </atlas-postal-code>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Rua"
                            name="street"
                            placeholder="Informe a rua"
                            value="${customer.address.street}"
                            required="true"
                            id="street"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Bairro"
                            name="province"
                            placeholder="Informe o bairro"
                            value="${customer.address.province}"
                            required="true"
                            id="province"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Número"
                            name="number"
                            placeholder="123"
                            value="${customer.address.number}"
                            required="true"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Cidade"
                            name="city"
                            placeholder="Informe a cidade"
                            value="${customer.address.city}"
                            required="true"
                            id="city"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Estado"
                            name="state"
                            maxlength="2"
                            placeholder="Informe o estado"
                            value="${customer.address.state}"
                            required="true"
                            id="state"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                 <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Complemento"
                            name="complement"
                            value="${customer.address.complement}"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <atlas-layout gap="2" inline="" wrap="">
                <atlas-button submit description="Salvar"></atlas-button>
            </atlas-layout>
        </atlas-form>
    </atlas-panel>
    <asset:javascript src="zipCode.js"/>
</body>
</html>
