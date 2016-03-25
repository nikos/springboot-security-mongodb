package de.nava.demo.service;

import de.nava.demo.domain.User;
import de.nava.demo.domain.UserCreateForm;
import de.nava.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String id) {
        log.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    public Collection<User> getAllUsers() {
        log.debug("Getting all users");
        return userRepository.findAll(new Sort("email"));
    }

    public User create(UserCreateForm form) {
        User user = User.build(form.getEmail(), form.getPassword(), form.getRole());
        return userRepository.save(user);
    }

}
