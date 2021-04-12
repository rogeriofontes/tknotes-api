package br.com.psi.tknotes.tknotesapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TknotesApiApplication

fun main(args: Array<String>) {
	runApplication<TknotesApiApplication>(*args)
}
