package br.com.psi.tknotes.tknotesapi.model

data class TokenResponse(
        val userId: Long? = 0L,
        val token: String = ""
        // val roles: Set<Profile>? = null
)