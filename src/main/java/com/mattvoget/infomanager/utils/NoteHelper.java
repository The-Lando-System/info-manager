package com.mattvoget.infomanager.utils;

import com.mattvoget.cryptutils.CryptUtils;
import com.mattvoget.infomanager.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteHelper {

    @Autowired
    CryptUtils cryptUtils;

    public Note encryptNote(Note note){
        try {
            note.setDetails(cryptUtils.encrypt(note.getDetails()));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return note;
    }

    public Note decryptNote(Note note){
        try {
            note.setDetails(cryptUtils.decrypt(note.getDetails()));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return note;
    }
}
