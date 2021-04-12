package br.com.psi.tknotes.tknotesapi.service.impl

import br.com.psi.tknotes.tknotesapi.model.Note
import br.com.psi.tknotes.tknotesapi.repository.NoteRepository
import br.com.psi.tknotes.tknotesapi.service.NoteService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
class NoteServiceImpl(private val noteRepository: NoteRepository) : NoteService {

    private val logger = LogManager.getLogger(NoteServiceImpl::class)

    override fun getAll(): ResponseEntity<List<Note>> = ResponseEntity.ok(noteRepository.findAll().toList())

    override fun add(note: Note): ResponseEntity<Note> {
        logger.warn("Note to add: $note")
        return noteRepository.save(note).let {
            ResponseEntity.created(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(it.id)
                            .toUri()).body(it)
        }
    }

    override fun getById(noteId: Long): ResponseEntity<Note> = noteRepository.findById(noteId).map { note ->
        ResponseEntity.ok(note)
    }.orElse(ResponseEntity.notFound().build())

    override fun put(noteId: Long, newNote: Note): ResponseEntity<Note> = noteRepository.findById(noteId).map { existingNote ->
        val updatedNote: Note = existingNote
                .copy(title = newNote.title, description = newNote.description)
        ResponseEntity.ok().body(noteRepository.save(updatedNote))
    }.orElse(ResponseEntity.notFound().build())

    override fun delete(noteId: Long): ResponseEntity<Void> = noteRepository.findById(noteId).map { note ->
        noteRepository.deleteById(note.id)
        ResponseEntity<Void>(HttpStatus.OK)
    }.orElse(ResponseEntity.notFound().build())
}