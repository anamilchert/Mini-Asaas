<atlas-sidebar 
    slot="sidebar"
    home-path="${createLink(controller: "customer", action: "index")}"
>   
    <atlas-sidebar-menu slot="body">
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
        <g:if test="${ user.authorities.any { it.authority == 'ROLE_ADMIN' } }">
             <atlas-sidebar-menu-item
                icon="user-plus"
                value="user-group"
                text="Usuários"
                ${ controllerName == "user" ? "active" : "" }
            >   
                <atlas-sidebar-menu-item
                    icon="user-plus"
                    value="user-group"
                    text="Criar Usuário"
                    href="${createLink(controller: "user", action: "index")}"
                    ${ controllerName == "user" && actionName == "index" ? "active" : "" }
                ></atlas-sidebar-menu-item>
            </atlas-sidebar-menu-item>
        </g:if>
    </atlas-sidebar-menu>
</atlas-sidebar>