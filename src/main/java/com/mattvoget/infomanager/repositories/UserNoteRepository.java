package com.mattvoget.infomanager.repositories;

import com.mattvoget.infomanager.models.UserNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserNoteRepository extends MongoRepository<UserNote, String>{

    List<UserNote> findByUsername(String username);

}
