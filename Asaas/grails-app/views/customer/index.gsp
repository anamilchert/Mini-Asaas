<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Criar Cliente</title>
</head>
<body>
    <h1>Criar Novo Cliente</h1>

    <g:form action="save">
        <label for="name">Nome:</label>
        <input type="text" name="name" id="name" required/><br/>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required/><br/>

        <label for="username">Username:</label>
        <input type="text" name="username" required/><br/>

        <label for="password">Senha:</label>
        <input type="password" name="password" required/><br/>

        <label for="email">Confirmar senha:</label>
        <input type="password" name="confirmedPassword" required/><br/>

        <label for="cpfCnpj">CPF/CNPJ:</label>
        <input type="text" name="cpfCnpj" id="cpfCnpj" required/><br/>

        <label for="personType">Tipo de Pessoa:</label>
        <select name="personType" id="personType" required>
            <option value="NATURAL">Física</option>
            <option value="LEGAL">Jurídica</option>
        </select><br/>

        <label for="street">Rua:</label>
        <input type="text" name="street" id="street" required/><br/>

        <label for="number">Número:</label>
        <input type="text" name="number" id="number" required/><br/>

        <label for="province">Bairro:</label>
        <input type="text" name="province" id="province" required/><br/>

        <label for="city">Cidade:</label>
        <input type="text" name="city" id="city" required/><br/>

        <label for="state">Estado:</label>
        <input type="text" name="state" id="state" required/><br/>

        <label for="complement">Complemento:</label>
        <input type="text" name="complement" id="complement"/><br/>

        <label for="zipCode">CEP:</label>
        <input type="text" name="zipCode" id="CEP" required/><br/>

        <input type="submit" value="Salvar"/>
    </g:form>
</body>
</html>
