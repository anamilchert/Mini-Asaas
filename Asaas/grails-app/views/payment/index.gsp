<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <form action="${createLink(controller:"payment", action:"save")}" method="post">
    <div>
      <label for="customerId"> Customer Id: 
        <input type="text" name="customerId" value="${customerId}">
      </label>
    </div>
    <g:select name="payerId" from="${payerList}" optionKey="id" optionValue="name"
      noSelection="['':'Selecione um pagador']" 
    />
    <div>
      <label for="value">
        Preço: 
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
          <option value="BOLETO">Boleto</option>
          <option value="CREDIT_CARD">Cartão de Crédito</option>
          <option value="DEBIT_CARD">Cartão de Débito</option>
          <option value="PIX">PIX</option>
        </select>
      </label>
    </div>

    <button type="submit">Registrar</button>
  </form>
</body>
</html>