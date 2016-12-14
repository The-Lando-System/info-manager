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

@Service
public class UserNoteService {

    private Logger log = LoggerFactory.getLogger(UserNoteService.class);

    @Autowired
    UserNoteRepository userNotesRepo;

    @Autowired
    NoteRepository noteRepository;

    @Transactional
    public Note createNote(Note note, User user){

        log.info("Creating a new note for user: " + user.getUsername());

        Note savedNote = noteRepository.save(note);

        UserNote userNote = new UserNote();
        userNote.setUsername(user.getUsername());
        userNote.setNoteId(savedNote.getId());

        userNotesRepo.save(userNote);

        return savedNote;
    }

}
