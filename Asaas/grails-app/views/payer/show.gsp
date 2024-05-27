<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <g:if test="${ payer }">
    <div>
        <input type="text" value="${payer.name}">
    </div>

    <div>
      <input type="text" value="${payer.email}">
    </div>

    <div>
      <input type="text"  value="${payer.phone}">
    </div>

    <div>
      <input type="text"  value="${payer.personType}">
    </div>

    <div>
      <input type="text" value="${payer.cpfCnpj}">
    </div>

    <div>
      <input type="text"  value="${payer.address.street}">
    </div>

    <div>
      <input type="text"  value="${payer.address.number}">
    </div>

    <div>
      <input type="text" value="${payer.address.state}">
    </div>

    <div>
      <input type="text" value="${payer.address.neighborhood}">
    </div>

    <div>
      <input type="text"  value="${payer.address.city}">
    </div>

    <div>
      <input type="text" value="${payer.address.CEP}">
    </div>

    <div>
      <input type="text" value="${payer.address.complement}">
    </div>

    <form action="${createLink(controller: "payer", action: "delete")}" method="post">
        <input type="hidden" name="id" value="${payer.id}" />
        <button type="submit">Excluir Pagador</button>
    </form>
  </g:if>
  <g:else>
     <p>Não foi possível registrar o pagador</p>
  </g:else>
</body>
</html>