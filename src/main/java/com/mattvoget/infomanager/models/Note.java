package com.mattvoget.infomanager.models;

import org.springframework.data.annotation.Id;

/**
 * Created by Matt on 12/2/2016.
 */
public class Note {


    @Id
    private String id;

    private String title;
    private String details;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
