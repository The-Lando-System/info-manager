package com.mattvoget.infomanager.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="test")
public class TestController {

    @PreAuthorize("@securityHelper.hasAccess()")
    @RequestMapping(value="/user", method= RequestMethod.GET)
    @ResponseBody
    public String userTest() {
        return "You have normal user access!";
    }

    @PreAuthorize("@securityHelper.isAdmin()")
    @RequestMapping(value="/admin", method= RequestMethod.GET)
    @ResponseBody
    public String adminTest() {
        return "You are an administrator!";
    }
}
