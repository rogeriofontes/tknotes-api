package br.com.psi.tknotes.tknotesapi.repository

import br.com.psi.tknotes.tknotesapi.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?
}