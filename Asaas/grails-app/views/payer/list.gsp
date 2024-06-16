<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
    <g:if test="${ payerList }">
        <g:each var="payer" in="${ payerList }">
            <div>
                <div>   
                    <div>Pagador: ${payer.name}</div>
                    <div>Telefone: ${payer.phone}</div>
                    <div>Email: ${payer.email}</div>
                </div>
                <g:if test="${  payer.deleted }"> 
                    <g:form controller="payer" action="restore" method="post" params="[id: payer.id]">
                        <input type="submit" value="Restaurar">
                    </g:form>
                </g:if>
            </div>
            <br>
            <hr>
        </g:each>
     </g:if>
    <g:else>
        <div>
            <p>Nenhum pagador registrado</p>
        </div>
    </g:else>
</body>
</html>