package br.com.psi.tknotes.tknotesapi.repository

import br.com.psi.tknotes.tknotesapi.model.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, Long> {}
