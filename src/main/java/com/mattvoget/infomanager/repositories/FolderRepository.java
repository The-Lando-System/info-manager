package com.mattvoget.infomanager.repositories;


import com.mattvoget.infomanager.models.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface FolderRepository extends MongoRepository<Folder, String> {
}
