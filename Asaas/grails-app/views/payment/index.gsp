<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registrar pagamento</title>
</head>
<body>
  <form action="${createLink(controller:"payment", action:"save")}" method="post">
    <div>
      <label for="customerId">Id: 
        <input type="text" name="customerId" value="${customerId}">
      </label>
    </div>
    <g:select name="payerId" from="${payerList}" optionKey="id" optionValue="name"
      noSelection="['':'Selecione um pagador']" 
    />
    <div>
      <label for="value">
        Valor: 
        <input type="text" name="value" placeholder="100,00">
      </label>
    </div>

    <div>
      <label for="dueDate">
        Data de vencimento: 
        <input type="date" name="dueDate" id="">
      </label>
    </div>

    <div>
      <label for="type">
        Forma de pagamento:
        <select name="type" id="">
        <g:each var="type" in="${ paymentTypes }">
          <option value="${type}">${type.getLabel()}</option>
        </g:each>
        </select>
      </label>
    </div>

    <button type="submit">Registrar</button>
  </form>
</body>
</html>