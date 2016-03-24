package de.nava.demo.service;

import de.nava.demo.domain.CurrentUser;
import de.nava.demo.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        log.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
