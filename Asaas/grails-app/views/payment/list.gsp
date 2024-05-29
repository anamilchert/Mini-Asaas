<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pagamentos</title>
</head>
<body>
    <g:if test="${ paymentList }">
       <g:each var="payment" in="${ paymentList }">
          <div>
            <div>${payment.value}</div>
            <div>${payment.status}</div>
            <div>${payment.type}</div>
            <div>-------------------------</div>
          </div>
       </g:each>
    </g:if>
    <g:else>
       <p>Nenhuma cobrança encontrada</p>
    </g:else>
</body>
</html>