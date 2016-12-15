package com.mattvoget.infomanager.repositories;

import com.mattvoget.infomanager.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
public interface NoteRepository extends MongoRepository<Note, String> {

}