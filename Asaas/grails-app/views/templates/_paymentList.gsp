<g:if test="${ paymentList }">
    <g:each var="payment" in="${ paymentList }">
        <div>
            <div>   
                <div>Pagador: ${payment.payer.name}</div>
                <div>Valor: ${payment.value}</div>
                <div>Status: ${payment.status}</div>
                <div>Forma de Pagamento: ${payment.type}</div>
            </div>
            <g:if test="${  payment.deleted }"> 
                <g:form controller="payment" action="restore" method="post" params="[id: payment.id]">
                    <input type="submit" value="Restaurar">
                </g:form>
            </g:if>
        </div>
        <br>
        <hr>
    </g:each>
 </g:if>
 <g:else>
    <p>Nenhuma cobrança encontrada</p>
 </g:else>
