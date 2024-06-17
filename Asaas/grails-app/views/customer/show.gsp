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
    <%-- <h1>Detalhes do Cliente</h1>

    <div>
     <g:if test="${customer}">
        <strong>Nome:</strong> ${customer.name}<br/>
        <strong>Email:</strong> ${customer.email}<br/>
        <strong>CPF/CNPJ:</strong> ${customer.cpfCnpj}<br/>
        <strong>Tipo de Pessoa:</strong> ${customer.personType}<br/>
        <h2>Endereço</h2>
        <strong>Rua:</strong> ${customer.address.street}<br/>
        <strong>Número:</strong> ${customer.address.number}<br/>
        <strong>Bairro:</strong> ${customer.address.province}<br/>
        <strong>Cidade:</strong> ${customer.address.city}<br/>
        <strong>Estado:</strong> ${customer.address.state}<br/>
        <strong>Complemento:</strong> ${customer.address.complement}<br/>
        <strong>CEP:</strong> ${customer.address.zipCode}<br/>
     </g:if>
    </div>
     <g:link action="edit" id="${customer?.id}" class="btn btn-primary">Editar Cliente</g:link> --%>
</body>
</html>
