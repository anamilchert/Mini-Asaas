<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">  
    <meta name="layout" content="external"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<atlas-panel header="Pagadores">
    <atlas-layout justify="space-between" alignment="center" inline>
        <atlas-button 
            type="outlined" 
            size="sm" 
            theme="secondary" 
            description="Voltar"
            href="${createLink(controller: "payer", action: "index")}"
            icon="arrow-left"
            pill
        >
        </atlas-button>
        <div>
            <atlas-layout inline gap="2" alignment="center">
                <atlas-form action="${createLink(customer: "payer", action: "list")}">
                    <atlas-checkbox name="includeDeleted" value="true">Deletados</atlas-checkbox>
                    <atlas-button 
                        description="Filtrar" 
                        theme="primary" 
                        size="sm"
                        submit
                    >
                    </atlas-button>
                </atlas-form>
            </atlas-layout>
        </div>
    </atlas-layout>
    <g:if test="${ payerList }">
        <g:each var="payer" in="${ payerList }">
            <atlas-card header="${payer.name}">
                <atlas-layout gap="2">
                    <atlas-col>
                        <atlas-text bold>Telefone:</atlas-text>
                        <atlas-text>${payer.phone} </atlas-text>
                        </atlas-col>
                    <atlas-col>
                        <atlas-text bold>Email:</atlas-text>
                        <atlas-text>${payer.email} </atlas-text>
                    </atlas-col>
                </atlas-layout>
                <atlas-divider spacing="6"></atlas-divider>
                <atlas-layout gap="2" inline>
                    <atlas-button 
                        description="Editar" 
                        theme="primary" 
                        size="sm"
                        icon="pencil"
                        href="${createLink(controller: "payer", action: "show", id: payer.id)}"
                    >
                    </atlas-button>
                    <g:if test="${ !payer.deleted }">
                        <atlas-button 
                            type="outlined" 
                            description="Deletar" 
                            theme="danger" 
                            size="sm"
                            icon="trash"
                            href="${createLink(controller: "payer", action: "delete", id: payer.id)}"
                        >
                        </atlas-button>
                    </g:if>
                </atlas-layout>
            </atlas-card>
        </g:each>
        <atlas-layout gap="2" alignment="center" justify="center">
            <atlas-link>
                <atlas-button 
                description="Cadastrar pagador" 
                theme="primary" 
                size="lg"
                href="${createLink(controller: "payer", action: "index", params: [customerId: customerId])}"
                 >
                 </atlas-button>
            </atlas-link>
        </atlas-layout>
    </g:if> 
    <g:else>
        <div>
            <p>Nenhum pagador registrado</p>
        </div>
    </g:else>
</atlas-panel>
</body>
</html>