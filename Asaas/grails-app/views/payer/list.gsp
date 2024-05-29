<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  <g:if test="${ payerList }">
    <form >
      <g:select name="payerId" from="${payerList}" optionKey="id" optionValue="name"
      noSelection="['':'Selecione um pagador']" />
    </form>
  </g:if>
  <g:else>
     <div>
      <p>Nenhum pagador registrado</p>
     </div>
  </g:else>
</body>
</html>