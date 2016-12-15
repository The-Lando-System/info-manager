package com.mattvoget.infomanager.repositories;

import com.mattvoget.infomanager.models.UserFolder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFolderRepository extends MongoRepository<UserFolder, String> {
    List<UserFolder> findByUsername(String username);
    UserFolder findByFolderId(String folderId);
}
