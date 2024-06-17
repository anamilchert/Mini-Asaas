<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="internal"/>
    <title>Criar usuário</title>
</head>
<body>
    <h1>Cadastro de Usuário</h1>
    <form action="${createLink(controller: "user", action: "save")}" method="post">
    
        <label for="name">Nome:</label>
        <input type="text" id="name" name="name" required>
        <br>

        <label for="username">username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        
        <label for="password">Senha:</label>
        <input type="password" id="password" name="password" required>
        <br>
        
        <label for="confirmedPassword">Confirmar Senha:</label>
        <input type="password" id="confirmedPassword" name="confirmedPassword" required>
        <br>
        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>