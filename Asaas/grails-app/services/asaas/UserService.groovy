package asaas

import asaas.adapter.UserAdapter
import asaas.Customer
import asaas.Role
import asaas.User
import asaas.UserRole

import grails.gorm.transactions.Transactional
import org.springframework.security.crypto.password.PasswordEncoder

@Transactional
class UserService {

    public User createUser(UserAdapter userAdapter, Long customerId, String roleName) {
        if (User.findByUsername(userAdapter.username)) {
            throw new RuntimeException("Usuário com nome '$username' já existe.")
        }

        if(userAdapter.password != userAdapter.confirmedPassword) {
            throw new RuntimeException("As senhas devem ser iguais.")
        }

        User user = new User()
        user.username = userAdapter.username
        user.name = userAdapter.name
        user.password = userAdapter.password
        user.customer = Customer.load(customerId)
        user.save(failOnError: true)

        Role role = Role.findByAuthority(roleName)

        if (!role) {
            role = new Role(authority: roleName).save(failOnError: true)
        }

        UserRole.create user, role

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        return user
    }
}