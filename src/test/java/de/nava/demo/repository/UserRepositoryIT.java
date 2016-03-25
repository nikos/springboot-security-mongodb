package de.nava.demo.repository;

import de.nava.demo.domain.User;
import de.nava.demo.testsupport.IntegrationTestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class UserRepositoryIT extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void mongoAccess() {
        User u = new User();
        u.setEmail("niko@nava.de");
        u.setPasswordHash("dsadashjgDJADGjhasgdjasghj");
        userRepository.save(u);

        //List<User> users = userRepository.findAll();
        Optional<User> fetched = userRepository.findOneByEmail("niko@nava.de");
        assertTrue(fetched.isPresent());
    }

}
