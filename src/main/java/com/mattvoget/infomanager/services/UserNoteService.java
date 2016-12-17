package com.mattvoget.infomanager.services;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.models.UserNote;
import com.mattvoget.infomanager.repositories.NoteRepository;
import com.mattvoget.infomanager.repositories.UserNoteRepository;
import com.mattvoget.infomanager.utils.NoteHelper;
import com.mattvoget.infomanager.utils.UserHelper;
import com.mattvoget.sarlacc.models.User;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    NoteHelper noteHelper;

    @Transactional
    public Note createNote(Note note, User user){

        log.info("Creating a new note for user: " + user.getUsername());

        Note savedNote = noteRepository.save(noteHelper.encryptNote(note));

        UserNote userNote = new UserNote();
        userNote.setUsername(user.getUsername());
        userNote.setNoteId(savedNote.getId());

        userNoteRepo.save(userNote);

        return noteHelper.decryptNote(savedNote);
    }

    public Note editNote(Note note, User user){

        log.info("Editing a note for user: " + user.getUsername());

        UserNote userNote = userNoteRepo.findByNoteId(note.getId());

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to edit this note!");

        return noteHelper.decryptNote(noteRepository.save(noteHelper.encryptNote(note)));
    }

    public List<Note> getUserNotes(User user){
        log.info("Retrieving all notes for user: " + user.getUsername());

        List<Note> notes = new ArrayList<Note>();

        for (UserNote userNote : userNoteRepo.findByUsername(user.getUsername())) {
            notes.add(noteHelper.decryptNote(noteRepository.findOne(userNote.getNoteId())));
        }

        return notes;
    }

    public Note getNoteById(String noteId, User user){
        log.info(String.format("Getting the following note for user %s: %s",user.getUsername(),noteId));

        UserNote userNote = userNoteRepo.findByNoteId(noteId);

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to view this note!");

        return noteHelper.decryptNote(noteRepository.findOne(noteId));
    }

    @Transactional
    public void deleteNote(String noteId, User user){
        log.info(String.format("Deleting the following note for user %s: %s",user.getUsername(),noteId));

        UserNote userNote = userNoteRepo.findByNoteId(noteId);

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to delete this note!");

        userNoteRepo.delete(userNote.getId());
        noteRepository.delete(userNote.getNoteId());
    }

}
