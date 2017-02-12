package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.services.UserNoteService;
import com.mattvoget.sarlacc.client.SarlaccUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mattvoget.sarlacc.client.SarlaccUserService.TOKEN_NAME;

@Controller
@RequestMapping(value="notes", produces= MediaType.APPLICATION_JSON_VALUE)
public class UserNotesController {

    @Autowired
    private SarlaccUserService sarlaccUserService;

    @Autowired
    UserNoteService userNoteService;

    @RequestMapping(value="/{folderId}", method= RequestMethod.POST)
    @ResponseBody
    public Note createUserNoteInFolder(@RequestHeader(value=TOKEN_NAME) String accessToken, @RequestBody Note note, @PathVariable String folderId ) {
        return userNoteService.createNote(note,folderId,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public List<Note> getUserNotes(@RequestHeader(value=TOKEN_NAME) String accessToken) {
        return userNoteService.getUserNotes(sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/", method= RequestMethod.PUT)
    @ResponseBody
    public Note editUserNote(@RequestHeader(value=TOKEN_NAME) String accessToken, @RequestBody Note note) {
        return userNoteService.editNote(note,sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{noteId}", method= RequestMethod.GET)
    @ResponseBody
    public Note getNoteById(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String noteId ) {
        return userNoteService.getNoteById(noteId, sarlaccUserService.getUser(accessToken));
    }

    @RequestMapping(value="/{noteId}/{folderId}", method= RequestMethod.DELETE)
    @ResponseBody
    public void deleteUserNote(@RequestHeader(value=TOKEN_NAME) String accessToken, @PathVariable String noteId, @PathVariable String folderId) {
        userNoteService.deleteNote(noteId,folderId,sarlaccUserService.getUser(accessToken));
    }

}
