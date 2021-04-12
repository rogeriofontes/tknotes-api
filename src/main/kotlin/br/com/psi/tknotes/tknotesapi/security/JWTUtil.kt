package br.com.psi.tknotes.tknotesapi.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*

/**
 * The type Jwt util.
 */
class JWTUtil private constructor() {

    companion object {
        /**
         * The constant EXPIRATIONTIME.
         */
        const val EXPIRATION_TIME: Long = 300000; //5 min //864000000 // 10 days

        /**
         * The constant SECRET.
         */
        const val SECRET = "ktnotes-token"

        /**
         * The constant TOKEN_PREFIX.
         */
        const val TOKEN_PREFIX = "Bearer"

        /**
         * The constant HEADER_STRING.
         */
        const val HEADER_STRING = "Authorization"

        /**
         * Create token string.
         *
         * @param email the email
         * @return the string
         */
        fun createToken(email: String?): String {
            val jwtToken = Jwts.builder().setSubject(email)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET).compact()
            return "$TOKEN_PREFIX $jwtToken"
        }
    }
}
