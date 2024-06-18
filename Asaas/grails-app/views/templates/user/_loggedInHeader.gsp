<header>
<atlas-layout gap="2" inline justify="end" alignment="center">
    <g:include controller="notification" action="list"/>
     <%-- <div>
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
                    ${ notification.status.isUnread() ? "is-new" : "" }
                    icon="tag"
                    theme="secondary"
                >
                    A cobrança no valor de  ${currency.formatCurrecy(currency: notification.payment.value)} de ${notification.payment.payer.name} foi ${notification.type.getLabel()}.
                </atlas-dropdown-item>
            </g:each>
        </atlas-dropdown>
    </div> --%>
    <div>
        <atlas-dropdown-button type="filled" size="md" theme="primary" description="${user.name}" icon="asaas-logo"> 
            <atlas-dropdown-item
                href="${createLink(controller: "payer", action: "list")}"
            >
                Pagadores
            </atlas-dropdown-item>
            <atlas-dropdown-item
                href="${createLink(controller: "payment", action: "list")}"
            >
                Cobranças
            </atlas-dropdown-item>
            <atlas-dropdown-item
                href="${createLink(controller: "logout", action: "index")}"
            >
                Sair
            </atlas-dropdown-item>
        </atlas-dropdown-button>
    </div>
</atlas-layout>
</header>
