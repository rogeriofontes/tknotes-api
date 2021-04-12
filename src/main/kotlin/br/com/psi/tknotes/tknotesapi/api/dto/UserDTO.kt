package br.com.psi.tknotes.tknotesapi.api.dto

import br.com.psi.tknotes.tknotesapi.model.User
import javax.validation.constraints.NotNull

class UserDTO(
        var id: Long,
        @NotNull
        var fullName: String,
        @NotNull
        var email: String,
        @NotNull
        var password: String
) {
    fun toUser() = User(0, fullName = fullName, email = email, password = password)
}