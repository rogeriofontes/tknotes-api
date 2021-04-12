package br.com.psi.tknotes.tknotesapi.service

import br.com.psi.tknotes.tknotesapi.model.Note
import org.springframework.http.ResponseEntity

interface NoteService {
    fun getAll(): ResponseEntity<List<Note>>
    fun add(note: Note): ResponseEntity<Note>
    fun getById(noteId: Long): ResponseEntity<Note>
    fun put(noteId: Long, newNote: Note): ResponseEntity<Note>
    fun delete(noteId: Long): ResponseEntity<Void>
}
