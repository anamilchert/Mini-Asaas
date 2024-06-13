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
            <option value="NATURAL">Física</option>
            <option value="LEGAL">Jurídica</option>
        </select><br/>

        <label for="street">Rua:</label>
        <input type="text" name="address.street" id="street" required/><br/>

        <label for="number">Número:</label>
        <input type="text" name="address.number" id="number" required/><br/>

        <label for="province">Bairro:</label>
        <input type="text" name="address.province" id="province" required/><br/>

        <label for="city">Cidade:</label>
        <input type="text" name="address.city" id="city" required/><br/>

        <label for="state">Estado:</label>
        <select name="address.state" id="state" required>
            <option value="">Selecione o estado...</option>
            <option value="AC">Acre</option>
            <option value="AL">Alagoas</option>
            <option value="AP">Amapá</option>
            <option value="AM">Amazonas</option>
            <option value="BA">Bahia</option>
            <option value="CE">Ceará</option>
            <option value="DF">Distrito Federal</option>
            <option value="ES">Espírito Santo</option>
            <option value="GO">Goiás</option>
            <option value="MA">Maranhão</option>
            <option value="MT">Mato Grosso</option>
            <option value="MS">Mato Grosso do Sul</option>
            <option value="MG">Minas Gerais</option>
            <option value="PA">Pará</option>
            <option value="PB">Paraíba</option>
            <option value="PR">Paraná</option>
            <option value="PE">Pernambuco</option>
            <option value="PI">Piauí</option>
            <option value="RR">Roraima</option>
            <option value="RO">Rondônia</option>
            <option value="RJ">Rio de Janeiro</option>
            <option value="RN">Rio Grande do Norte</option>
            <option value="RS">Rio Grande do Sul</option>
            <option value="SC">Santa Catarina</option>
            <option value="SP">São Paulo</option>
            <option value="SE">Sergipe</option>
            <option value="TO">Tocantins</option>
        </select><br/>

        <label for="complement">Complemento:</label>
        <input type="text" name="address.complement" id="complement"/><br/>

        <label for="zipCode">CEP:</label>
        <input type="text" name="address.zipCode" id="CEP" required/><br/>

        <input type="submit" value="Salvar"/>
    </g:form>
</body>
</html>
