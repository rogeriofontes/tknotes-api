package br.com.psi.tknotes.tknotesapi.exceptions

import java.util.*

data class ErrorModel(val timestamp: Date, val message: String, val details: String)
