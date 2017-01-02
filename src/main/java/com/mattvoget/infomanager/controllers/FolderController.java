
package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.config.SarlaccUserService;
import com.mattvoget.infomanager.models.Folder;
import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.services.FolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mattvoget.infomanager.config.SarlaccUserService.TOKEN_NAME;

@Controller
@RequestMapping(value="folders", produces= MediaType.APPLICATION_JSON_VALUE)
public class FolderController extends ErrorHandlingController {

    private Logger log = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private SarlaccUserService sarlaccUserService;

    @Autowired
    FolderService folderService;

    @RequestMapping(value="/", method= RequestMethod.POST)
    @ResponseBody
    public Folder createFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @RequestBody Folder folder) {
        return folderService.createFolder(folder,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public List<Folder> getUserFolders(@RequestHeader(value=TOKEN_NAME) String accessToken) {
        return folderService.getFolders(sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/", method= RequestMethod.PUT)
    @ResponseBody
    public Folder editFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @RequestBody Folder folder) {
        return folderService.editFolder(folder,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}", method= RequestMethod.GET)
    @ResponseBody
    public Folder getFolderById(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId) {
        return folderService.getFolderById(folderId,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}", method= RequestMethod.DELETE)
    @ResponseBody
    public void deleteFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId) {
        folderService.deleteFolder(folderId,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}/notes", method= RequestMethod.GET)
    @ResponseBody
    public List<Note> getNotesInFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId ) {
        return folderService.getNotesInFolder(folderId,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}/{noteId}", method= RequestMethod.POST)
    @ResponseBody
    public Folder addNoteToFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId, @PathVariable String noteId ) {
        return folderService.addNoteToFolder(folderId, noteId, sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}/{noteId}", method= RequestMethod.DELETE)
    @ResponseBody
    public Folder removeNoteFromFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId, @PathVariable String noteId ) {
        return folderService.removeNoteFromFolder(folderId, noteId, sarlaccUserService.getUser(accessToken));
    }
}
