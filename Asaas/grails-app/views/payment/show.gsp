<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <g:if test="${ payment }">
     <div>
      <input type="text" value="${payment.customer}">
      <input type="text" value="${payment.payer}">
      <input type="text" value="${payment.value}">
      <input type="text" value="${payment.method}">
      <input type="text" value="${payment.status}">
      <input type="text" value="${payment.maturityDate}">
     </div>
  </g:if>
</body>
</html>