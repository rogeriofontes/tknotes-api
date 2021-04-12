package br.com.psi.tknotes.tknotesapi.model

import javax.persistence.*

@Entity
@Table(name = "tb_note")
data class Note(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                //@JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
                val id: Long = 0L,
                val title: String = "",
                val description: String = "",
                @ManyToOne//(fetch = FetchType.LAZY, optional = false)
                @JoinColumn(name = "user_id", referencedColumnName = "id")
                val user: User

)