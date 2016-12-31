package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.config.SarlaccUserService;
import com.mattvoget.sarlacc.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="sarlacc-test", produces= MediaType.APPLICATION_JSON_VALUE)
public class TestUserController {

    @Autowired
    private SarlaccUserService sarlaccUserService;

    @RequestMapping(value="/{accessToken}", method= RequestMethod.GET)
    @ResponseBody
    public User login(@PathVariable String accessToken) {
        return sarlaccUserService.getUser(accessToken);
    }

}
