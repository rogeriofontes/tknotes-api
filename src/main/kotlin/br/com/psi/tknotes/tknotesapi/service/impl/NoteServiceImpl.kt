package br.com.psi.tknotes.tknotesapi.service.impl

import br.com.psi.tknotes.tknotesapi.model.Note
import br.com.psi.tknotes.tknotesapi.repository.NoteRepository
import br.com.psi.tknotes.tknotesapi.service.NoteService
import org.apache.logging.log4j.LogManager
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
//@CacheConfig(cacheNames = ["notesInCache"])
class NoteServiceImpl(private val noteRepository: NoteRepository) : NoteService {

    private val logger = LogManager.getLogger(NoteServiceImpl::class)

    @Cacheable("notesInCache")
    override fun getAll(): List<Note> = noteRepository.findAll().toList()

    @Cacheable("notesInCache")
    override fun add(note: Note): Note {
        logger.warn("Note to add: $note")
        return noteRepository.save(note)
    }

    @Cacheable("notesInCache")
    override fun getById(id: Long): Optional<Note> = noteRepository.findById(id)

    @Cacheable("notesInCache")
    override fun getByTitle(title: String): Optional<Note> = noteRepository.findByTitle(title)

    @CacheEvict(value = ["notesInCache"], allEntries = true)
    override fun put(noteId: Long, newNote: Note): Optional<Note> = noteRepository.findById(noteId).map { existingNote ->
        val updatedNote: Note = existingNote
                .copy(title = newNote.title, description = newNote.description)
        return@map noteRepository.save(updatedNote)
    }

    @CacheEvict(value = ["notesInCache"], allEntries = true)
    override fun delete(noteId: Long) = noteRepository.deleteById(noteId)
}