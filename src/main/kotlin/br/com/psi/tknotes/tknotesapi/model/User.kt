package br.com.psi.tknotes.tknotesapi.model

import br.com.psi.tknotes.tknotesapi.api.dto.UserDTO
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
        val id: Long = 0,
        @Column(name = "full_name")
        var fullName: String = "",
        var email: String = "",
        @JsonProperty(value = "password", access = JsonProperty.Access.READ_ONLY)
        var password: String = ""
) {
    fun toDTO() = UserDTO(id = this.id, fullName = this.fullName, email = this.email, password = this.password)
}