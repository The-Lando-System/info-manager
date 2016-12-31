package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.models.Folder;
import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.services.FolderService;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

//@Controller
//@RequestMapping(value="folders", produces= MediaType.APPLICATION_JSON_VALUE)
public class FolderController {

//    private Logger log = LoggerFactory.getLogger(FolderController.class);
//
//    @Autowired
//    SecurityHelper securityHelper;
//
//    @Autowired
//    FolderService folderService;
//
//    @RequestMapping(value="/", method= RequestMethod.POST)
//    @ResponseBody
//    public Folder createFolder(@RequestBody Folder folder, @RequestHeader(value="x-access-token") String accessToken) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.createFolder(folder,securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/", method= RequestMethod.GET)
//    @ResponseBody
//    public List<Folder> getUserFolders(@RequestHeader(value="x-access-token") String accessToken) {
//        log.info("Session ID on the request is: " + RequestContextHolder.currentRequestAttributes().getSessionId());
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return folderService.getFolders(user);
//    }
//
//    @RequestMapping(value="/", method= RequestMethod.PUT)
//    @ResponseBody
//    public Folder editFolder(@RequestBody Folder folder, @RequestHeader(value="x-access-token") String accessToken) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.editFolder(folder,securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/{folderId}", method= RequestMethod.GET)
//    @ResponseBody
//    public Folder getFolderById(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String folderId ) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.getFolderById(folderId, securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/{folderId}", method= RequestMethod.DELETE)
//    @ResponseBody
//    public void deleteFolder(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String folderId) {
//        securityHelper.checkAccess(accessToken);
//        folderService.deleteFolder(folderId,securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/{folderId}/notes", method= RequestMethod.GET)
//    @ResponseBody
//    public List<Note> getNotesInFolder(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String folderId ) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.getNotesInFolder(folderId, securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/{folderId}/{noteId}", method= RequestMethod.POST)
//    @ResponseBody
//    public Folder addNoteToFolder(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String folderId, @PathVariable String noteId ) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.addNoteToFolder(folderId, noteId, securityHelper.getUser());
//    }
//
//    @RequestMapping(value="/{folderId}/{noteId}", method= RequestMethod.DELETE)
//    @ResponseBody
//    public Folder removeNoteFromFolder(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String folderId, @PathVariable String noteId ) {
//        securityHelper.checkAccess(accessToken);
//        return folderService.removeNoteFromFolder(folderId, noteId, securityHelper.getUser());
//    }
}
