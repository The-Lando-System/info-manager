package com.mattvoget.infomanager.services;

import com.mattvoget.infomanager.models.Folder;
import com.mattvoget.infomanager.models.UserFolder;
import com.mattvoget.infomanager.repositories.FolderRepository;
import com.mattvoget.infomanager.repositories.UserFolderRepository;
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
public class FolderService {
    private Logger log = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    UserFolderRepository userFolderRepo;

    @Autowired
    FolderRepository folderRepository;

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

        if (!StringUtils.equals(userFolder.getUsername(),user.getUsername())){
            throw new IllegalAccessError("You are not allowed to edit this folder!");
        }

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

        if (!StringUtils.equals(userFolder.getUsername(),user.getUsername())){
            throw new IllegalAccessError("You are not allowed to view this folder!");
        }

        return folderRepository.findOne(folderId);
    }

    @Transactional
    public void deleteFolder(String folderId, User user){
        log.info(String.format("Deleting the following folder for user %s: %s",user.getUsername(),folderId));

        UserFolder userFolder = userFolderRepo.findByFolderId(folderId);

        if (!StringUtils.equals(userFolder.getUsername(),user.getUsername())){
            throw new IllegalAccessError("You are not allowed to delete this folder!");
        }

        userFolderRepo.delete(userFolder.getId());
        folderRepository.delete(userFolder.getFolderId());
    }

}
