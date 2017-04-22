package com.mattvoget.infomanager.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.models.UserFolder;
import com.mattvoget.infomanager.models.UserNote;
import com.mattvoget.infomanager.repositories.FolderRepository;
import com.mattvoget.infomanager.repositories.NoteRepository;
import com.mattvoget.infomanager.repositories.UserFolderRepository;
import com.mattvoget.infomanager.repositories.UserNoteRepository;
import com.mattvoget.infomanager.utils.NoteHelper;
import com.mattvoget.infomanager.utils.UserHelper;
import com.mattvoget.sarlacc.models.Role;
import com.mattvoget.sarlacc.models.User;

@Service
public class UserNoteService {

    private Logger log = LoggerFactory.getLogger(UserNoteService.class);

    @Autowired UserNoteRepository userNoteRepo;
    @Autowired UserFolderRepository userFolderRepo;
    @Autowired NoteRepository noteRepository;
    @Autowired FolderRepository folderRepository;
    @Autowired FolderService folderService;
    @Autowired NoteHelper noteHelper;

    private int demoNotes = 0;
    
    @Transactional
    public Note createNote(Note note, String folderId, User user){

        log.info(String.format("Creating a new note for user %s in folder %s", user.getUsername(), folderId));
        
        if (user.getRole() == Role.DEMO){
        	if (demoNotes > 5){
        		throw new IllegalArgumentException("Cannot create more than 5 notes per folder for a DEMO user");
        	}
        	demoNotes++;
        }


        Note savedNote = noteRepository.save(noteHelper.encryptNote(note));

        UserNote userNote = new UserNote();
        userNote.setUsername(user.getUsername());
        userNote.setNoteId(savedNote.getId());

        userNoteRepo.save(userNote);
        folderService.addNoteToFolder(folderId,savedNote.getId(),user);

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
    public void deleteNote(String noteId, String folderId, User user){
        log.info(String.format("Deleting the following note with id %s for user %s from folder %s",noteId,user.getUsername(),folderId));

        UserNote userNote = userNoteRepo.findByNoteId(noteId);

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to delete this note!");

        folderService.removeNoteFromFolder(folderId,userNote.getNoteId(),user);
        
        if (user.getRole() == Role.DEMO){
        	if (demoNotes > 0){
        		demoNotes--;
        	}
        }


        userNoteRepo.delete(userNote.getId());
        noteRepository.delete(userNote.getNoteId());

    }
    
    public List<Note> getNotesInFolder(String folderId, User user) {
        log.info(String.format("Retrieving all notes in for user %s in folder: %s",user.getUsername(),folderId));
        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to access notes for this folder!");

        List<Note> notes = new ArrayList<>();

        for (String noteId : folderRepository.findOne(folderId).getNoteIds()){
            notes.add(noteHelper.decryptNote(noteRepository.findOne(noteId)));
        }
        
        if (user.getRole() == Role.DEMO){
        	demoNotes = notes.size();
        }


        return notes;
    }


}
