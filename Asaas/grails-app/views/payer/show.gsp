<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <g:if test="${ payer }">
    <h1>Detalhes do Pagador</h1>
    <g:if test="${ flash.message }">
       <p>${flash.message}</p>
    </g:if>
    <g:form controller="payer" action="update" id="${payer.id}">
        <div>
            <label for="name">Nome:</label>
            <g:textField id="nameField" name="name" value="${payer.name}" />
        </div>

        <div>
            <label for="email">Email:</label>
            <g:textField id="emailField" name="email" value="${payer.email}"  />
        </div>

        <div>
            <label for="phone">Telefone:</label>
            <g:textField id="phoneField" name="phone" value="${payer.phone}"  />
        </div>

        <div>
            <label for="personType">Tipo de Pessoa:</label>
            <g:textField id="personTypeField" name="personType" value="${payer.personType}" disabled="${true}" />
        </div>

        <div>
            <label for="cpfCnpj">CPF/CNPJ:</label>
            <g:textField id="cpfCnpjField" name="cpfCnpj" value="${payer.cpfCnpj}" disabled="${true}" />
        </div>

        <div>
            <h2>Endereço:</h2>      
            <div>
                <label for="street">Rua:</label>
                <g:textField id="streetField" name="street" value="${payer.address.street}"  />
            </div>
            <div>
                <label for="number">Número:</label>
                <g:textField id="numberField" name="number" value="${payer.address.number}"  />
            </div>
            <div>
                <label for="state">Estado:</label>
                <g:textField id="stateField" name="state" value="${payer.address.state}"  />
            </div>
            <div>
                <label for="province">Bairro:</label>
                <g:textField id="provinceField" name="province" value="${payer.address.province}"  />
            </div>
            <div>
                <label for="city">Cidade:</label>
                <g:textField id="cityField" name="city" value="${payer.address.city}"  />
            </div>
            <div>
                <label for="zipCode">CEP:</label>
                <g:textField id="zipCodeField" name="zipCode" value="${payer.address.zipCode}"  />
            </div>
            <div>
                <label for="complement">Complemento:</label>
                <g:textField id="complementField" name="complement" value="${payer.address.complement}"  />
            </div>
        </div>  

        <g:link action="index">Voltar</g:link>
        <input type="submit" value="Salvar" />
    </g:form>
  </g:if>
  <g:else>
     <p>Não foi possível registrar o pagador</p>
  </g:else>
</body>
</html>