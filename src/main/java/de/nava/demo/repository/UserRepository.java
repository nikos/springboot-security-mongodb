package de.nava.demo.repository;

import de.nava.demo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findOneByEmail(String email);

}
