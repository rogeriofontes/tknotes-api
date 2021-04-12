package br.com.psi.tknotes.tknotesapi.api.controller

import br.com.psi.tknotes.tknotesapi.api.dto.UserDTO
import br.com.psi.tknotes.tknotesapi.model.User
import br.com.psi.tknotes.tknotesapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun list(): ResponseEntity<List<UserDTO>>
            = userService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserDTO>?
            = userService.getById(id)

    @PostMapping
    fun add(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO>
            = userService.add(userDTO)

    @PutMapping("{id}")
    fun put(@PathVariable id: Long, @RequestBody userDTO: UserDTO): ResponseEntity<User>?
            = userService.put(userId = id, userDTO.toUser())

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void>? = userService.delete(userId = id)

    @GetMapping("/me")
    fun me() = ResponseEntity.ok(userService.myself()!!)
}


