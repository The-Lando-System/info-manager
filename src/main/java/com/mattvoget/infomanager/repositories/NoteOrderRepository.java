package com.mattvoget.infomanager.repositories;

import com.mattvoget.infomanager.models.NoteOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoteOrderRepository extends MongoRepository<NoteOrder, String> {

    NoteOrder findByFolderId(String folderId);

}
