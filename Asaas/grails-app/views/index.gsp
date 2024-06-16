<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <asset:stylesheet src="index.css"/>
</head>
<body>
    <div class="main-container">
        <div class="image-container"><atlas-image src="${resource(dir: 'images', file: 'asaas.png')}" height="500"></atlas-image></div>
        <div class="center-content">
            <atlas-heading size="h1" class="main-text">Bem Vindo ao Mini Asaas,</atlas-heading>
            <atlas-text size="lg">o lugar ideal para você gerenciar suas cobranças com eficiência.</atlas-text>
                <div class="button-container">
                    <atlas-layout gap="5">
                        <atlas-button 
                            icon="asaas-logo"
                            size="lg" 
                            theme="primary" 
                            description="Login"
                            block=""
                            href="${createLink(controller: "auth", action: "login")}"
                        >
                        </atlas-button>
                        <atlas-button 
                            type="ghost" 
                            size="md" 
                            theme="primary" 
                            description="Criar conta"
                            href="${createLink(controller: "customer", action: "index")}"
                        >
                        </atlas-button>
                    </atlas-layout>
                </div>
        </div>
    </div>
</body>
</html>
