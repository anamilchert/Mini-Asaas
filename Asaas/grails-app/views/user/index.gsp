<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="internal"/>
    <title>Criar usuário</title>
</head>
<body>
    <atlas-panel>
        <g:if test="${ flash.message }">
            <atlas-alert message="${flash.message}" type="success"></atlas-alert>
        </g:if>
        <g:if test="${ flash.error }">
            <atlas-alert message="${flash.error}" type="error"></atlas-alert>
        </g:if>
        <atlas-form action="${createLink(customer: "user", action: "save")}">
            <atlas-row>
                <atlas-col>
                    <atlas-text
                        size="lg"
                        bold=""
                    >
                        Criar usuário
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
            </atlas-grid>
            <atlas-button submit description="Salvar"></atlas-button>
        </atlas-form>
    </atlas-panel>
</body>
</html>