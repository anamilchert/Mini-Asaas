package asaas

import asaas.User

import grails.plugin.springsecurity.SpringSecurityService

abstract class BaseController {

    SpringSecurityService springSecurityService

    protected Long getCurrentCustomerId() {
        return springSecurityService.currentUser.customer.id
    }
    
    protected User getCurrentUser() {
        return springSecurityService.currentUser
    }
} 