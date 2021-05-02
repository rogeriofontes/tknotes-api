package br.com.psi.tknotes.tknotesapi.repository

import br.com.psi.tknotes.tknotesapi.model.Note
import org.springframework.data.repository.CrudRepository
import java.util.*

interface NoteRepository : CrudRepository<Note, Long> {
    fun findByTitle(tile: String): Optional<Note>
}
