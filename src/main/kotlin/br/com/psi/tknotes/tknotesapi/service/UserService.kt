package br.com.psi.tknotes.tknotesapi.service

import br.com.psi.tknotes.tknotesapi.api.dto.UserDTO
import br.com.psi.tknotes.tknotesapi.model.TokenResponse
import br.com.psi.tknotes.tknotesapi.model.User
import org.springframework.http.ResponseEntity

interface UserService {
    fun getAll(): ResponseEntity<List<UserDTO>>
    fun add(userDTO: UserDTO): ResponseEntity<UserDTO>
    fun getById(userId: Long): ResponseEntity<UserDTO>
    fun put(userId: Long, newUser: User): ResponseEntity<User>
    fun delete(userId: Long): ResponseEntity<Void>
    fun validated(user: User): TokenResponse?
    fun myself(): String?
}