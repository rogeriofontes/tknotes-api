package br.com.psi.tknotes.tknotesapi.service.impl

import br.com.psi.tknotes.tknotesapi.model.User
import br.com.psi.tknotes.tknotesapi.security.JWTUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * The type Token authentication service.
 */
@Service
class TokenAuthenticationService {

    /**
     * Add authentication.
     *
     * @param res      the res
     * @param username the username
     */
    fun addAuthentication(res: HttpServletResponse, username: String?) {
        val jwt: String = Jwts.builder().setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + JWTUtil.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact()
        res.addHeader(JWTUtil.HEADER_STRING, JWTUtil.TOKEN_PREFIX + " " + jwt)
    }

    /**
     * Gets authentication.
     *
     * @param request the request
     * @return the authentication
     */
    fun getAuthentication(request: HttpServletRequest): Authentication? {
        return request.getHeader(JWTUtil.HEADER_STRING)?.let {
            val user: String = getUser(it)
            return UsernamePasswordAuthenticationToken(user, null, emptyList())
        }
    }

    fun getUser(token: String) : String {
        return Jwts.parser().setSigningKey(JWTUtil.SECRET)
                .parseClaimsJws(token.replace(JWTUtil.TOKEN_PREFIX, "")).getBody().getSubject()
    }


    /*val token = request.getHeader(JWTUtil.HEADER_STRING)
      if (token != null) {
          // parse the token.
          val user: String = Jwts.parser().setSigningKey(JWTUtil.SECRET)
                  .parseClaimsJws(token.replace(JWTUtil.TOKEN_PREFIX, "")).getBody().getSubject()
          return if (user != null) UsernamePasswordAuthenticationToken(user, null, emptyList()) else null
      }
      return null*/
}
