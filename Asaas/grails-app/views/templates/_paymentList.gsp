<g:if test="${ paymentList }">
    <g:each var="payment" in="${ paymentList }">
        <div>   
            <div>Pagador: ${payment.payer.name}</div>
            <div>Valor: ${payment.value}</div>
            <div>Status: ${payment.status}</div>
            <div>Forma de Pagamento: ${payment.type}</div>
        </div>
        <br>
    </g:each>
 </g:if>
 <g:else>
    <p>Nenhuma cobrança encontrada</p>
 </g:else>
