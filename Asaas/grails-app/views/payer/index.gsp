<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
    <g:if test="${ flash.message }">
       <p>${flash.message}</p>
    </g:if>
  
  <form action="${createLink(controller:"payer", action:"save")}" method="post">
    <div>
      <label for="name">
        Nome
        <input type="text" name="name">
      </label>
    </div>

    <div>
      <label for="email">
        Email
        <input type="text" name="email">
      </label>
    </div>

    <div>
      <label for="phone">
        Telefone
        <input type="text" name="phone">
      </label>
    </div>

    <label for="">
      Tipo de pessoa
      <select name="personType" id="">
      <option value="NATURAL">Pessoa Física</option>
      <option value="LEGAL">Pessoa Jurídica </option>
    </select>
    </label>
    

    <div>
      <label for="cpfCnpj">
        CPF/CNPJ
        <input type="text" name="cpfCnpj">
      </label>
    </div>

    <div>
      <label for="street">
        Rua
        <input type="text" name="street">
      </label>
    </div>

    <div>
      <label for="number">
        Número
        <input type="text" name="number">
      </label>
    </div>

    <div>
      <label for="state">
        Estado
        <input type="text" name="state">
      </label>
    </div>

    <div>
      <label for="province">
        Bairro
        <input type="text" name="province">
      </label>
    </div>
    <div>
      <label for="city">
        Cidade
        <input type="text" name="city">
      </label>
    </div>

    <div>
      <label for="zipCode">
        CEP
        <input type="text" name="zipCode">
      </label>
    </div>

    <div>
      <label for="complement">
        Complemento
        <input type="text" name="complement">
      </label>
    </div>

    <input type="submit" value="Salvar">
  </form>
</body>
</html>