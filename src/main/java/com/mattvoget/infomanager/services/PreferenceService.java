package com.mattvoget.infomanager.services;

import com.mattvoget.infomanager.models.Preference;
import com.mattvoget.infomanager.repositories.PreferenceRepository;
import com.mattvoget.sarlacc.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PreferenceService {

    private Logger log = LoggerFactory.getLogger(PreferenceService.class);

    @Autowired private PreferenceRepository preferenceRepository;

    @Transactional
    public void savePrimaryFolderPreference(String folderId, User user){

        log.info("Saving primary folder preference for user: " + user.getUsername());

        Preference pref = preferenceRepository.findByUsername(user.getUsername());

        if (pref == null){
            pref = new Preference();
            pref.setUsername(user.getUsername());
        }

        pref.setPrimaryFolderId(folderId);

        preferenceRepository.save(pref);

    }

    @Transactional
    public Preference findPreferenceByUser(User user){
        log.info("Retrieving preferences for user: " + user.getUsername());
        return preferenceRepository.findByUsername(user.getUsername());
    }

}
