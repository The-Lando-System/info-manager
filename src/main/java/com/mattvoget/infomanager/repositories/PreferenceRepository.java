package com.mattvoget.infomanager.repositories;


import com.mattvoget.infomanager.models.Preference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PreferenceRepository extends MongoRepository<Preference, String> {

    Preference findByUsername(String username);

}
