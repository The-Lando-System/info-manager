package com.mattvoget.infomanager.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Folder {

    @Id
    private String id;
    private String name;
    private List<String> noteIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNoteIds() {
        return noteIds;
    }

    public void setNoteIds(List<String> noteIds) {
        this.noteIds = noteIds;
    }
}
