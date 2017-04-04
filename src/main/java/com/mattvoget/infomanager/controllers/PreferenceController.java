package com.mattvoget.infomanager.controllers;

import com.mattvoget.infomanager.models.Preference;
import com.mattvoget.infomanager.services.PreferenceService;
import com.mattvoget.sarlacc.client.SarlaccUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.mattvoget.sarlacc.client.SarlaccUserService.TOKEN_NAME;

@Controller
@RequestMapping(value="preferences", produces= MediaType.APPLICATION_JSON_VALUE)
public class PreferenceController extends ErrorHandlingController {

    @Autowired private SarlaccUserService sarlaccUserService;
    @Autowired private PreferenceService preferenceService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public Preference getPrimaryFolderPreference(@RequestHeader(value=TOKEN_NAME) String accessToken) {
        return preferenceService.findPreferenceByUser(sarlaccUserService.getUser(accessToken));
    }

}
