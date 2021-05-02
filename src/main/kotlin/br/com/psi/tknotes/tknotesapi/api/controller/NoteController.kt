package br.com.psi.tknotes.tknotesapi.api.controller

import br.com.psi.tknotes.tknotesapi.exceptions.ResourceFoundException
import br.com.psi.tknotes.tknotesapi.exceptions.ResourceNotFoundException
import br.com.psi.tknotes.tknotesapi.model.Note
import br.com.psi.tknotes.tknotesapi.service.NoteService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@Api(value = "NoteController", description = "Restful APIs related to Note")
@RestController
@RequestMapping("notes")
class NoteController {

    @Autowired
    lateinit var noteService: NoteService

    @ApiOperation(
            value = "Get all note objects",
            response = List::class,
            tags = ["List notes"]
    )
    @ApiResponses(
            ApiResponse(code = 200, message = "Success|OK"),
            ApiResponse(code = 404, message = "not found")
    )
    @GetMapping
    fun list(): ResponseEntity<List<Note>> {
        var noteList = noteService.getAll()
        if (noteList.isEmpty())
            throw ResourceNotFoundException("Não existe dados")

        return  ResponseEntity.ok(noteList)
    }

    @ApiOperation(
            value = "Get note object by Id",
            response = Note::class,
            tags = ["Get notes by id"]
    )
    @ApiResponses(
            ApiResponse(code = 200, message = "Success|OK"),
            ApiResponse(code = 404, message = "not found")
    )
    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Note> = noteService.getById(id).map { note ->
        ResponseEntity.ok(note)
    }.orElseThrow { ResourceNotFoundException("Note doesn't exist : $id") }

    @ApiOperation(
            value = "create new Note",
            response = Note::class,
            tags = ["Add notes"]
    )
    @ApiResponses(
            ApiResponse(code = 201, message = "Success|CREATED"),
            ApiResponse(code = 404, message = "not found")
    )
    @PostMapping
    fun add(@RequestBody note: Note): ResponseEntity<Note> {

        var noteFound = noteService.getByTitle(note.title)
        if (noteFound.isPresent)
            throw ResourceFoundException("Nota já está cadastrada!")

        noteService.add(note).let { note ->
            return ResponseEntity.created(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(note.id)
                            .toUri()).body(note)
        }
    }

    @ApiOperation(
            value = "Update note object by Id",
            response = Note::class,
            tags = ["Edit notes"]
    )
    @ApiResponses(
            ApiResponse(code = 200, message = "Success|OK"),
            ApiResponse(code = 404, message = "not found")
    )
    @PutMapping("{id}")
    fun edit(@PathVariable id: Long, @Valid @RequestBody note: Note): ResponseEntity<Note> {

        var noteFound = noteService.getById(id)
        if (noteFound.isPresent)
            throw ResourceFoundException("Nota já está cadastrada!")

        return noteService.put(noteId = id, note).map { updatedNote ->
            ResponseEntity.ok().body(updatedNote)
        }.orElseThrow { ResourceNotFoundException("Note doesn't exist : $id") }
    }

    @ApiOperation(
            value = "delete note object by Id",
            response = Note::class,
            tags = ["Delete notes"]
    )
    @ApiResponses(
            ApiResponse(code = 200, message = "Success|OK"),
            ApiResponse(code = 404, message = "not found")
    )
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void>? {
        return noteService.getById(id).map { note ->
            noteService.delete(note.id)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElseThrow { ResourceNotFoundException("Note doesn't exist : $id") }
    }
}


