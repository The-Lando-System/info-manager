
package com.mattvoget.infomanager.controllers;

import static com.mattvoget.sarlacc.client.SarlaccUserService.TOKEN_NAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mattvoget.infomanager.models.Folder;
import com.mattvoget.infomanager.models.NoteOrder;
import com.mattvoget.infomanager.services.FolderService;
import com.mattvoget.infomanager.services.PreferenceService;
import com.mattvoget.sarlacc.client.SarlaccUserService;

@Controller
@RequestMapping(value="folders", produces= MediaType.APPLICATION_JSON_VALUE)
public class FolderController extends ErrorHandlingController {

    @Autowired private SarlaccUserService sarlaccUserService;
    @Autowired private FolderService folderService;
    @Autowired private PreferenceService preferenceService;

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

    @RequestMapping(value="/make-primary/{folderId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void makeFolderPrimary(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId) {
        preferenceService.savePrimaryFolderPreference(folderId, sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{folderId}/note-order", method = RequestMethod.GET)
    @ResponseBody
    public NoteOrder getNoteOrder(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String folderId) {
        return folderService.getNoteOrder(folderId, sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/note-order", method = RequestMethod.POST)
    @ResponseBody
    public NoteOrder setNoteOrder(@RequestHeader(value=TOKEN_NAME) String accessToken, @RequestBody NoteOrder noteOrder) {
        return folderService.setNoteOrder(noteOrder, sarlaccUserService.getUser(accessToken));
    }
}
