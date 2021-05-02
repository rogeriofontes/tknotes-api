package br.com.psi.tknotes.tknotesapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class ResourceFoundException(message: String) : Exception(message)