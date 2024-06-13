package asaas.adapter

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UserAdapter {

  String username

  String name

  String password

  String confirmedPassword

  public UserAdapter(Map params) {
    this.username = params.username
    this.name = params.name
    this.password = params.password
    this.confirmedPassword = params.confirmedPassword
  }
}