package br.com.psi.tknotes.tknotesapi.api.controller

import br.com.psi.tknotes.tknotesapi.model.TokenResponse
import br.com.psi.tknotes.tknotesapi.model.User
import br.com.psi.tknotes.tknotesapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/login")
class LoginController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    fun signup(@RequestBody user: User): ResponseEntity<TokenResponse> {
        val tokenResponse = userService.validated(user)
        return ResponseEntity.created(URI("")).body(tokenResponse)
    }

}
