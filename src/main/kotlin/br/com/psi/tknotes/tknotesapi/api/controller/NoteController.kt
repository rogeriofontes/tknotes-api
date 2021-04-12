package br.com.psi.tknotes.tknotesapi.api.controller

import br.com.psi.tknotes.tknotesapi.model.Note
import br.com.psi.tknotes.tknotesapi.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("notes")
class NoteController {

    @Autowired
    lateinit var noteService: NoteService

    @GetMapping
    fun list(): ResponseEntity<List<Note>>
            = noteService.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Note>?
            = noteService.getById(id)

    @PostMapping
    fun add(@RequestBody note: Note): ResponseEntity<Note>
            = noteService.add(note)

    @PutMapping("{id}")
    fun put(@PathVariable id: Long, @Valid @RequestBody note: Note): ResponseEntity<Note>?
            = noteService.put(noteId = id,note)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void>? = noteService.delete(noteId = id)

}


