package com.mattvoget.infomanager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.mattvoget.infomanager.models.Note;

@Component
public interface NoteRepository extends MongoRepository<Note, String> {

}