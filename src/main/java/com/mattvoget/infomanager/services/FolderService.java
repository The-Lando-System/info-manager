package com.mattvoget.infomanager.services;

import com.mattvoget.infomanager.models.Folder;
import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.models.UserFolder;
import com.mattvoget.infomanager.models.UserNote;
import com.mattvoget.infomanager.repositories.FolderRepository;
import com.mattvoget.infomanager.repositories.NoteRepository;
import com.mattvoget.infomanager.repositories.UserFolderRepository;
import com.mattvoget.infomanager.repositories.UserNoteRepository;
import com.mattvoget.infomanager.utils.NoteHelper;
import com.mattvoget.infomanager.utils.UserHelper;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderService {
    private Logger log = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    UserFolderRepository userFolderRepo;

    @Autowired
    UserNoteRepository userNoteRepo;

    @Autowired
    FolderRepository folderRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteHelper noteHelper;

    @Transactional
    public Folder createFolder(Folder folder, User user){

        log.info("Creating a new folder for user: " + user.getUsername());

        Folder savedFolder = folderRepository.save(folder);

        UserFolder userFolder = new UserFolder();
        userFolder.setUsername(user.getUsername());
        userFolder.setFolderId(savedFolder.getId());

        userFolderRepo.save(userFolder);

        return savedFolder;
    }

    public Folder editFolder(Folder folder, User user){

        log.info("Editing a folder for user: " + user.getUsername());

        UserFolder userFolder = userFolderRepo.findByFolderId(folder.getId());

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to edit this folder!");

        return folderRepository.save(folder);
    }

    public List<Folder> getFolders(User user){
        log.info("Retrieving all folders for user: " + user.getUsername());

        List<Folder> folders = new ArrayList<>();

        for (UserFolder userFolder : userFolderRepo.findByUsername(user.getUsername())) {
            folders.add(folderRepository.findOne(userFolder.getFolderId()));
        }

        return folders;
    }

    public Folder getFolderById(String folderId, User user){
        log.info(String.format("Getting the following folder for user %s: %s",user.getUsername(),folderId));

        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to view this folder!");

        return folderRepository.findOne(folderId);
    }

    @Transactional
    public void deleteFolder(String folderId, User user){
        log.info(String.format("Deleting the following folder and its associated notes for user %s: %s",user.getUsername(),folderId));

        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to delete this folder!");

        Folder folder = folderRepository.findOne(userFolder.getFolderId());

        int i = 0;

        for (String noteId : folder.getNoteIds()){
            UserNote userNote = userNoteRepo.findByNoteId(noteId);
            userNoteRepo.delete(userNote);
            noteRepository.delete(noteId);
            i++;
        }

        log.info(String.format("Deleted %d notes from the folder",i));

        userFolderRepo.delete(userFolder.getId());
        folderRepository.delete(userFolder.getFolderId());
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

        return notes;
    }

    @Transactional
    public Folder addNoteToFolder(String folderId, String noteId, User user){
        log.info(String.format("Adding note %s to folder %s for user %s",noteId,folderId,user.getUsername()));

        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);
        UserNote userNote = userNoteRepo.findByNoteId(noteId);

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to add notes to this folder");

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to add this note to your folder");

        Folder folder = folderRepository.findOne(folderId);
        folder.getNoteIds().add(noteId);
        return folderRepository.save(folder);
    }

    @Transactional
    public Folder removeNoteFromFolder(String folderId, String noteId, User user){
        log.info(String.format("Removing note %s from folder %s for user %s",noteId,folderId,user.getUsername()));

        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);
        UserNote userNote = userNoteRepo.findByNoteId(noteId);

        UserHelper.checkUsernames(userFolder.getUsername(),user.getUsername(),
                "You are not allowed to remove notes from this folder");

        UserHelper.checkUsernames(userNote.getUsername(),user.getUsername(),
                "You are not allowed to remove this note from your folder");

        Folder folder = folderRepository.findOne(folderId);
        folder.getNoteIds().remove(noteId);
        return folderRepository.save(folder);
    }
}
