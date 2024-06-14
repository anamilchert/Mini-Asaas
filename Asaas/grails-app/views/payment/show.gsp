<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mostrar Pagamento</title>
</head>
<body>
    <g:if test="${ flash.error }">
       <p>${flash.error}</p>
    </g:if>
    <g:if test="${ flash.message }">
       <p>${flash.message}</p>
    </g:if>
    <g:form controller="payment" action="update" method="post" params="[id: payment?.id]">
        <div>
            <label for="payer">Pagador:</label>
            <input type="text" id="payer" name="payerId" value="${payment.payer.id}" readonly>
        </div>
        <div>
            <label for="status">Status:</label>
            <input type="text" id="status" name="status" value="${payment.status}" readonly>
        </div>
        <div>
            <label for="value">Valor:</label>
            <input type="text" id="value" name="value" value="${payment.value}">
        </div>
        <div>
            <label for="type"> Forma de pagamento: </label>
            <g:select name="type" from="${paymentTypeList}" optionKey="${type}" optionValue="label" value="${payment.type}" />
        </div>
        <div>
            <label for="dueDate">Vencimento:</label>
            <input type="date" id="dueDate" name="dueDate" value="${date.formatDate(date: payment.dueDate)}">
        </div>
        <div>
            <g:link action="index" params="[customerId:payment.customer.id]">Voltar</g:link>
            <g:if test="${ payment.status.isPending() }">
                <input type="submit" value="Editar" />
            </g:if>
        </div>
    </g:form>

    
    <g:if test="${  payment.status.isPending() }"> 
        <div>
            <h3>Cancelar cobrança</h3>
        </div>  
        <g:form controller="payment" action="delete" method="post" params="[id: payment.id]">
            <input type="submit" value="Deletar">
        </g:form>
    </g:if>

      <div>
        <h3>Confirmar recebimento em dinheiro</h3>
    </div>
    <g:if test="${ payment.status.isPending() }">
        <g:form controller="payment" action="confirmReceivedInCash" method="post" params="[id: payment.id]">
            <input type="submit" value="Confirmar recebimento">
        </g:form>
    </g:if>

</body>
</html>