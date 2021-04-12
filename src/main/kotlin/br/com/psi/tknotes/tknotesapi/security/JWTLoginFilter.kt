package br.com.psi.tknotes.tknotesapi.security

import br.com.psi.tknotes.tknotesapi.model.Credentials
import br.com.psi.tknotes.tknotesapi.service.impl.TokenAuthenticationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * The type Jwt login filter.
 */
class JWTLoginFilter(url: String?,
                     authManager: AuthenticationManager?,
                     var tokenAuthenticationService: TokenAuthenticationService) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val credentials: Credentials = ObjectMapper().readValue(req.inputStream, Credentials::class.java)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.email,
                credentials.password, emptyList()))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain,
                                          auth: Authentication) {
        tokenAuthenticationService.addAuthentication(res, auth.name)
    }

    /**
     * Instantiates a new Jwt login filter.
     *
     * @param url         the url
     * @param authManager the auth manager
     */
    init {
        authenticationManager = authManager
    }
}
