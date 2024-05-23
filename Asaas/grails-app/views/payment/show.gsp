<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mostrar Pagamento</title>
</head>
<body>
  <g:if test="${ payment }">
     <div>
      <input type="text" value="${payment.customer}">
      <input type="text" value="${payment.payer}">
      <input type="text" value="${payment.value}">
      <input type="text" value="${payment.type}">
      <input type="text" value="${payment.status}">
      <input type="text" value="${payment.dueDate}">
     </div>
  </g:if>
</body>
</html>