<atlas-sidebar 
    slot="sidebar"
    home-path="${createLink(controller: "customer", action: "index")}"
>   
    <atlas-sidebar-menu slot="body">

        <atlas-sidebar-menu-item
            icon="dashboard"
            value="dashboard-group"
            text="Resumo"
            href="${createLink(controller: "dashboard", action: "index")}"
            ${ controllerName == "dashboard" && actionName == "index" ? "active" : "" }
        ></atlas-sidebar-menu-item>
    
        <atlas-sidebar-menu-item
            icon="users"
            value="payers-group"
            text="Pagadores"
            ${ controllerName == "payer" ? "active" : "" }
        >   
            <atlas-sidebar-menu-item
                icon="users"
                value="payers-group"
                text="Criar Pagador"
                href="${createLink(controller: "payer", action: "index")}"
                ${ controllerName == "payer" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                icon="users"
                value="payers-group"
                text="Lista de Pagadores"
                href="${createLink(controller: "payer", action: "list")}"
                ${ controllerName == "payer" && actionName == "list" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>

        <atlas-sidebar-menu-item
            icon="money"
            value="payments-group"
            text="Cobranças"
            ${ controllerName == "payment" ? "active" : "" }
        >   
            <atlas-sidebar-menu-item
                icon="money"
                value="payments-group"
                text="Criar Cobrança"
                href="${createLink(controller: "payment", action: "index")}"
                ${ controllerName == "payment" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                icon="money"
                value="payments-group"
                text="Lista de Cobranças"
                href="${createLink(controller: "payment", action: "list")}"
                ${ controllerName == "payment" && actionName == "list" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>