package com.mattvoget.infomanager.repositories;

import com.mattvoget.infomanager.models.UserNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserNoteRepository extends MongoRepository<UserNote, String>{

}
