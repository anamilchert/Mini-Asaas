package asaas

import asaas.BaseController
import asaas.adapter.UserAdapter
import asaas.UserService

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

@Secured("IS_AUTHENTICATED_FULLY")
class UserController extends BaseController{

    UserService userService

    @Secured("ROLE_ADMIN")
    def index() {
        
    }

    @Secured("ROLE_ADMIN")
    def save() {
        try {
            Long customerId = getCurrentCustomerId() as Long
            UserAdapter userAdapter = new UserAdapter(params)
            User user = userService.createUser(userAdapter, customerId, "ROLE_USER")
            redirect(action:"show", id:user.id)
        } catch (ValidationException validationException) {
            String errorsMessage = validationException.errors.allErrors.defaultMessage.join(", ")
            flash.error = "Não foi possível criar um usuário: $errorsMessage"
            redirect(action: "index")
        } catch (Exception exception) {
            flash.error = "Erro ao criar um usuário. Por favor, contate o time de suporte"
            redirect(action: "index")
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show() {
        User user = User.read(params.id)
        
        if (user) return [user: user]

        redirect(action: "index")
    }

}
