<header>
<atlas-layout inline justify="end">
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
