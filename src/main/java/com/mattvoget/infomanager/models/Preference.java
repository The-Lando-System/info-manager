package com.mattvoget.infomanager.models;


import org.springframework.data.annotation.Id;

public class Preference {

    @Id
    private String id;
    private String username;
    private String primaryFolderId;

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

    public String getPrimaryFolderId() {
        return primaryFolderId;
    }

    public void setPrimaryFolderId(String primaryFolderId) {
        this.primaryFolderId = primaryFolderId;
    }
}
