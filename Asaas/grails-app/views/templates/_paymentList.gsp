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
