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

        <label for="cpfCnpj">CPF/CNPJ:</label>
        <input type="text" name="cpfCnpj" id="cpfCnpj" required/><br/>

        <label for="personType">Tipo de Pessoa:</label>
        <select name="personType" id="personType" required>
            <option value="FISICA">Física</option>
            <option value="JURIDICA">Jurídica</option>
        </select><br/>

        <label for="street">Rua:</label>
        <input type="text" name="address.street" id="street" required/><br/>

        <label for="number">Número:</label>
        <input type="text" name="address.number" id="number" required/><br/>

        <label for="neighborhood">Bairro:</label>
        <input type="text" name="address.neighborhood" id="neighborhood" required/><br/>

        <label for="city">Cidade:</label>
        <input type="text" name="address.city" id="city" required/><br/>

        <label for="state">Estado:</label>
        <input type="text" name="address.state" id="state" required/><br/>

        <label for="complement">Complemento:</label>
        <input type="text" name="address.complement" id="complement"/><br/>

        <label for="CEP">CEP:</label>
        <input type="text" name="address.CEP" id="CEP" required/><br/>

        <input type="submit" value="Salvar"/>
    </g:form>
</body>
</html>
