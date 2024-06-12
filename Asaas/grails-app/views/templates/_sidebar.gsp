<atlas-sidebar 
    slot="sidebar"
    home-path="${createLink(controller: "customer", action: "index")}"
>
    <atlas-sidebar-menu slot="body">
        <atlas-sidebar-menu-item
            icon="users"
            value="clients-group"
            text="Pagadores"
            ${ controllerName == "payer" ? "active" : "" }
        >
            <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Lista de Pagadores"
                href="${createLink(controller: "payer", action: "list", params: [customerId: 1])}"
                ${ controllerName == "payer" && actionName == "list" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>