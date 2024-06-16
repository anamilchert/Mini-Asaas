<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="external"/>
    <title>Lista de Cobranças</title>
</head>
<body>
    <atlas-panel header="Cobranças">
        <atlas-layout justify="space-between" alignment="center" inline>
            <atlas-button 
                type="outlined" 
                size="sm" 
                theme="secondary" 
                description="Voltar"
                href="${createLink(controller: "payment", action: "index")}"
                icon="arrow-left"
                pill
            >
            </atlas-button>
            <div>
                <atlas-layout inline gap="2" alignment="center">
                    <atlas-form action="${createLink(customer: "payment", action: "list")}">
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
        <g:if test="${ paymentList }">
            <g:each var="payment" in="${ paymentList }">
                <atlas-card>
                    <atlas-layout gap="2">
                        <atlas-col>
                            <atlas-text bold>Pagador:</atlas-text>
                            <atlas-text>${payment.payer.name} </atlas-text>
                        </atlas-col>
                        <atlas-col>
                            <atlas-text bold>Valor:</atlas-text>
                            <atlas-text>${payment.value} </atlas-text>
                        </atlas-col>
                        <atlas-col>
                            <atlas-text bold>Status:</atlas-text>
                            <atlas-text>${payment.status.getLabel()} </atlas-text>
                        </atlas-col>
                        <atlas-col>
                            <atlas-text bold>Forma de Pagamento:</atlas-text>
                            <atlas-text>${payment.type.getLabel()} </atlas-text>
                        </atlas-col>
                    </atlas-layout>
                    <atlas-divider spacing="6"></atlas-divider>
                    <atlas-layout gap="2" inline>
                        <g:if test="${ payment.status.isPending() }">
                            <atlas-button 
                                description="Editar" 
                                theme="primary" 
                                size="sm"
                                icon="pencil"
                                href="${createLink(controller: "payment", action: "show", id: payment.id)}"
                            >
                            </atlas-button>
                        </g:if>
                        
                        <g:if test="${ !payment.deleted && payment.status.isPending() }">
                            <atlas-button 
                                type="outlined" 
                                description="Deletar" 
                                theme="danger" 
                                size="sm"
                                icon="trash"
                                href="${createLink(controller: "payment", action: "delete", id: payment.id)}"
                            >
                            </atlas-button>
                        </g:if>
                    </atlas-layout>
                </atlas-card>
            </g:each>
            <atlas-layout gap="2" alignment="center" justify="center">
                <atlas-link>
                    <atlas-button 
                    description="Criar Cobrança" 
                    theme="primary" 
                    size="lg"
                    href="${createLink(controller: "payment", action: "index")}"
                     >
                     </atlas-button>
                </atlas-link>
            </atlas-layout>
        </g:if> 
        <g:else>
            <div>
                <p>Nenhuma cobrança registrada</p>
            </div>
        </g:else>
    </atlas-panel>
</body>
</html>