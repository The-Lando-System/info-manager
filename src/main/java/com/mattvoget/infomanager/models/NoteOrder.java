package com.mattvoget.infomanager.models;


import org.springframework.data.annotation.Id;

import java.util.Map;

public class NoteOrder {

    @Id
    private String id;
    private String username;
    private String folderId;
    private Map<String,Integer> noteOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public Map<String, Integer> getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(Map<String, Integer> noteOrder) {
        this.noteOrder = noteOrder;
    }
}
