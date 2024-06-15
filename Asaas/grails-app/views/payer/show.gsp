<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="external"/>
    <title>Document</title>
</head>
<body>
    <g:if test="${ flash.message }">
        <atlas-alert message="${flash.message}" type="success"></atlas-alert>
    </g:if>
    <g:if test="${ flash.error }">
        <atlas-alert message="${flash.error}" type="error"></atlas-alert>
    </g:if>
    <atlas-panel>
        <atlas-form action="${createLink(customer: "payer", action: "update", params: [id: payer.id])}">
            <atlas-text
                size="lg"   
                bold=""
            >
                Atualizar dados de ${payer.name.toUpperCase()}
            </atlas-text>
            <atlas-grid>
                <atlas-row>
                    <atlas-col>
                        <atlas-input
                            label="Nome"
                            name="name"
                            value="${payer.name}"
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
                            value="${payer.email}"
                            required="true"
                            disabled
                        >
                        </atlas-masked-input>
                    </atlas-col>
                </atlas-row>
                <atlas-row>
                    <atlas-col>
                        <atlas-masked-input
                            label="Telefone"
                            name="phone"
                            mask-alias="phone"
                            label="Input de Celular"
                            value="${payer.phone}"
                            disabled="${payer.deleted}"
                            required="true"
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
                            value="${payer.cpfCnpj}"
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
                            value="${payer.personType}"
                            required="true"
                            disabled="${payer.deleted}"
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
                        value="${payer.address.zipCode}"
                        disabled="${payer.deleted}"
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
                            value="${payer.address.street}"
                            disabled="${payer.deleted}"
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
                            value="${payer.address.province}"
                            disabled="${payer.deleted}"
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
                            value="${payer.address.number}"
                            disabled="${payer.deleted}"
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
                            value="${payer.address.city}"
                            disabled="${payer.deleted}"
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
                            value="${payer.address.state}"
                            disabled="${payer.deleted}"
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
                            value="${payer.address.complement}"
                            disabled="${payer.deleted}"
                        >
                        </atlas-input>
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <g:if test="${ !payer.deleted }">
                  <atlas-layout gap="2" inline="" wrap="">
                    <atlas-button submit description="Salvar"></atlas-button>
                    <atlas-button 
                        type="outlined" 
                        size="md" 
                        theme="danger" 
                        description="Cancelar"
                        href="${createLink(controller: "payer", action: "index")}"
                    >
                    </atlas-button>
                </atlas-layout>
            </g:if>
        </atlas-form>

        
        <atlas-form action="${createLink(customer: "payer", action: "restore", params: [id: payer.id])}">
            <atlas-button submit description="Restaurar"></atlas-button>
        </atlas-form>
    </atlas-panel>
</body>
</html>