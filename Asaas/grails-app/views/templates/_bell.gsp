<div>
    <atlas-button 
        type="ghost" 
        size="md" 
        theme="primary" 
        description="" 
        icon="bell" 
        data-atlas-dropdown="test-dropdown"
    ></atlas-button>
    <atlas-dropdown id="test-dropdown" auto-close="" trigger="click" auto-close-trigger="outside">
        <g:each var="notification" in="${ notificationList }">
            <atlas-dropdown-item
                icon="tag"
                theme="secondary"
            >
                A cobrança no valor de  ${currency.formatCurrecy(currency: notification.payment.value)} de ${notification.payment.payer.name} foi ${notification.type.getLabel()}.
            </atlas-dropdown-item>
        </g:each>
    </atlas-dropdown>
</div>