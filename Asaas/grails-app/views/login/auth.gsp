<!DOCTYPE html>
<html lang="en">
<head>
     <meta name="layout" content="main"/>
    <title>Login</title>
    <asset:stylesheet src="login.css"/>
</head>
<body>
     <div class="container">
        <div class="login-form">
            <div class="image-container">
                <atlas-image src="${resource(dir: 'images', file: 'asaas-blue.png')}" height="500"></atlas-image>
            </div>
            <div class="form-container">
            <atlas-form action="${createLink(customer: "login", action: "authenticate")}" method="POST">
                <atlas-grid container>
                    <atlas-row>
                        <atlas-col>
                            <atlas-heading size="h3" >Entre com sua conta</atlas-heading>
                        <atlas-col>
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
                        <atlas-col>
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
                <atlas-grid>
                    <atlas-button submit description="Entrar" block></atlas-button>
            </atlas-form>
        </div>
        </div>
    </div>
</body>
</html>