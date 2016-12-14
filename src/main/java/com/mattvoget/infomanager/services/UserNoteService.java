package com.mattvoget.infomanager.services;

import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.models.UserNote;
import com.mattvoget.infomanager.repositories.NoteRepository;
import com.mattvoget.infomanager.repositories.UserNoteRepository;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserNoteService {

    private Logger log = LoggerFactory.getLogger(UserNoteService.class);

    @Autowired
    UserNoteRepository userNoteRepo;

    @Autowired
    NoteRepository noteRepository;

    @Transactional
    public Note createNote(Note note, User user){

        log.info("Creating a new note for user: " + user.getUsername());

        Note savedNote = noteRepository.save(note);

        UserNote userNote = new UserNote();
        userNote.setUsername(user.getUsername());
        userNote.setNoteId(savedNote.getId());

        userNoteRepo.save(userNote);

        return savedNote;
    }

    public List<Note> getUserNotes(User user){
        log.info("Retrieving all notes for user: " + user.getUsername());

        List<Note> notes = new ArrayList<Note>();

        for (UserNote userNote : userNoteRepo.findByUsername(user.getUsername())) {
            notes.add(noteRepository.findOne(userNote.getNoteId()));
        }

        return notes;
    }

}
