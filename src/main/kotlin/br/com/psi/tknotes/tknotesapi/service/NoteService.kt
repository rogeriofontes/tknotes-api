package br.com.psi.tknotes.tknotesapi.service

import br.com.psi.tknotes.tknotesapi.model.Note
import java.util.*

interface NoteService {
    fun getAll(): List<Note>
    fun add(note: Note): Note
    fun getById(noteId: Long): Optional<Note>
    fun getByTitle(title: String): Optional<Note>
    fun put(noteId: Long, newNote: Note): Optional<Note>
    fun delete(noteId: Long)
}
