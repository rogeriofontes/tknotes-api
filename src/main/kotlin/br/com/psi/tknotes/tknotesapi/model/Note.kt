package br.com.psi.tknotes.tknotesapi.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
@Table(name = "tb_note")
@ApiModel(description = "Class representing Notes object in the application.")
data class Note(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                //@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
                val id: Long = 0L,
                @ApiModelProperty(notes = "Note title", name = "title", required = true, value = "title")
                val title: String = "",
                @ApiModelProperty(notes = "Note description", name = "description", required = true, value = "description")
                val description: String = "",
                @ApiModelProperty(notes = "Note user", name = "user", required = true, value = "user")
                @ManyToOne//(fetch = FetchType.LAZY, optional = false)
                @JoinColumn(name = "user_id", referencedColumnName = "id")
                val user: User

)