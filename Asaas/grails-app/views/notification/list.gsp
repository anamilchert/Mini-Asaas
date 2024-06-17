<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link
        rel="stylesheet"
        href="https://atlas.asaas.com/v15.18.0/atlas.min.css"
        crossorigin="anonymous">
    <script
        defer
        src="https://atlas.asaas.com/v15.18.0/atlas.min.js"
        crossorigin="anonymous"
    ></script>
</head>
<body>
    <div>
        <atlas-button 
            type="ghost" 
            size="sm" 
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
    </div>
</body>
</html>