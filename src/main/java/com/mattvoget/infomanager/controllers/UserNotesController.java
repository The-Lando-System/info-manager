package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.services.UserNoteService;
import com.mattvoget.sarlacc.client.authentication.ErrorHandlingController;
import com.mattvoget.sarlacc.client.authentication.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="notes", produces= MediaType.APPLICATION_JSON_VALUE)
public class UserNotesController extends ErrorHandlingController {

    @Autowired
    SecurityHelper securityHelper;

    @Autowired
    UserNoteService userNoteService;

    @RequestMapping(value="/", method= RequestMethod.POST)
    @ResponseBody
    public Note createUserNote(@RequestBody Note note, @RequestHeader(value="x-access-token") String accessToken) {
        securityHelper.checkAccess(accessToken);
        return userNoteService.createNote(note,securityHelper.getUser());
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public List<Note> getUserNotes(@RequestHeader(value="x-access-token") String accessToken) {
        securityHelper.checkAccess(accessToken);
        return userNoteService.getUserNotes(securityHelper.getUser());
    }

    @RequestMapping(value="/", method= RequestMethod.PUT)
    @ResponseBody
    public Note editUserNote(@RequestBody Note note, @RequestHeader(value="x-access-token") String accessToken) {
        securityHelper.checkAccess(accessToken);
        return userNoteService.editNote(note,securityHelper.getUser());
    }

    @RequestMapping(value="/{noteId}", method= RequestMethod.GET)
    @ResponseBody
    public Note getNoteById(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String noteId ) {
        securityHelper.checkAccess(accessToken);
        return userNoteService.getNoteById(noteId, securityHelper.getUser());
    }

    @RequestMapping(value="/{noteId}", method= RequestMethod.DELETE)
    @ResponseBody
    public void deleteUserNote(@RequestHeader(value="x-access-token") String accessToken, @PathVariable String noteId) {
        securityHelper.checkAccess(accessToken);
        userNoteService.deleteNote(noteId,securityHelper.getUser());
    }

}
