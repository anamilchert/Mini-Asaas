<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Criar Cliente</title>
</head>
<body>

    <atlas-panel>
        <g:if test="${ flash.message }">
            <atlas-alert message="${flash.message}" type="success"></atlas-alert>
        </g:if>
        <g:if test="${ flash.error }">
            <atlas-alert message="${flash.error}" type="error"></atlas-alert>
        </g:if>
        <atlas-form action="${createLink(customer: "customer", action: "save")}">
            <atlas-row>
                <atlas-col>
                    <atlas-text
                        size="lg"
                        bold=""
                    >
                        Criar Conta
                    </atlas-text>
                </atlas-col>
            </atlas-row>
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Nome"
                            name="name"
                            required="true"
                            placeholder="Informe um nome"
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
                            placeholder="pagador@exemplo.com"
                            required="true"
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                 <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Username"
                            name="username"
                            required="true"
                            placeholder="Informe um username"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-password-input 
                            label="Senha" 
                            name="password"
                            placeholder="Informe um password"
                            required=""
                        >
                        </atlas-password-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-password-input 
                            label="Confirme a senha" 
                            name="confirmedPassword"
                            placeholder="Confirme a senha"
                            required=""
                        >
                        </atlas-password-input>
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
                <div id="inputContainer">

                </div>
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="CPF/CNPJ"
                            name="cpfCnpj"
                            mask-alias="cpf-cnpj"
                            required="true"
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
                        id="zipCode"
                        required="true">
                        </atlas-postal-code>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Rua"
                            name="street"
                            placeholder="Informe a rua"
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
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <atlas-button submit description="Salvar"></atlas-button>
        </atlas-form>
    </atlas-panel>
    <asset:javascript src="zipCode.js"/>
</body>
</html>
