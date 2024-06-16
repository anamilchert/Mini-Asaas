<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>MIni Asaas</title>
    <asset:stylesheet src="index.css"/>
</head>
<body>
    <div class="container">
        <div class="main-container">
            <atlas-image src="${resource(dir: 'images', file: 'asaas.png')}" height="400"></atlas-image>
            <div class="center-content">
                <atlas-heading size="h1" class="main-text">Bem Vindo ao Mini Asaas,</atlas-heading>
                <atlas-text size="lg">o lugar ideal para você gerenciar suas cobranças com eficiência.</atlas-text>
                <div class="buttons-container">
                    <atlas-grid container>
                        <atlas-row>
                            <atlas-col>
                                <atlas-button 
                                    icon="asaas-logo"
                                    size="lg" 
                                    theme="primary" 
                                    description="Login"
                                    block
                                    href="${createLink(controller: "auth", action: "login")}"
                                >
                                </atlas-button>
                            </atlas-col>
                        </atlas-row>
                            <atlas-row>
                                <atlas-col>
                                    <atlas-button 
                                        type="ghost" 
                                        size="md" 
                                        theme="secondary" 
                                        description="Criar conta"
                                        block
                                        href="${createLink(controller: "customer", action: "index")}"
                                    >
                                    </atlas-button>
                                </atlas-col>
                            </atlas-row>
                    </atlas-grid>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
