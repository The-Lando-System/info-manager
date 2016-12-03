package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.models.Note;
import com.mattvoget.infomanager.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;

    @PreAuthorize("@securityHelper.hasAccess()")
    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public List<Note> getUsers() {
        return noteRepo.findAll();
    }


}
