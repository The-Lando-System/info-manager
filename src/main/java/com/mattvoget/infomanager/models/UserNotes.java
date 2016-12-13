package com.mattvoget.infomanager.models;


import java.util.List;

public class UserNotes {

    private String id;
    private String username;
    private List<String> noteIds;

    public UserNotes() {}

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

    public List<String> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(List<String> noteIds) {
        this.noteIds = noteIds;
    }
}
