package br.com.psi.tknotes.tknotesapi.service.impl

import br.com.psi.tknotes.tknotesapi.api.dto.UserDTO
import br.com.psi.tknotes.tknotesapi.model.TokenResponse
import br.com.psi.tknotes.tknotesapi.model.User
import br.com.psi.tknotes.tknotesapi.model.UserDetailsImpl
import br.com.psi.tknotes.tknotesapi.repository.UserRepository
import br.com.psi.tknotes.tknotesapi.security.JWTUtil
import br.com.psi.tknotes.tknotesapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    override fun getAll(): ResponseEntity<List<UserDTO>> = ResponseEntity.ok(userRepository.findAll().map { user->
        user.toDTO()
    })


    override fun add(userDTO: UserDTO): ResponseEntity<UserDTO> {
        var user = userDTO.toUser()
        user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user).let {
            ResponseEntity.created(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(it.id)
                            .toUri()).body(it.toDTO())
        }
    }

    override fun getById(userId: Long): ResponseEntity<UserDTO> = userRepository.findById(userId).map { user ->
        ResponseEntity.ok(user.toDTO())
    }.orElse(ResponseEntity.notFound().build())

    override fun put(userId: Long, newUser: User): ResponseEntity<User> = userRepository.findById(userId).map { existingUser ->
        val updatedUser: User = existingUser
                .copy(fullName = newUser.fullName, email = newUser.email)
        ResponseEntity.ok().body(userRepository.save(updatedUser))
    }.orElse(ResponseEntity.notFound().build())

    override fun delete(userId: Long): ResponseEntity<Void> = userRepository.findById(userId).map { user ->
        userRepository.deleteById(user.id)
        ResponseEntity<Void>(HttpStatus.OK)
    }.orElse(ResponseEntity.notFound().build())

    override fun validated(user: User): TokenResponse? {
        val userResult = userDetailsService.loadUserByUsername(user.email)
        return findByEmail(userResult.username).let { foundedUser ->
            return@let TokenResponse(foundedUser?.id, JWTUtil.createToken(foundedUser?.email))
        }
    }

    override fun myself(): String? {
        return userRepository.findByEmail(getCurrentUserEmail())?.fullName
    }

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }
}