package de.nava.demo.repository;

import de.nava.demo.domain.Role;
import de.nava.demo.domain.User;
import de.nava.demo.testsupport.IntegrationTestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserRepositoryIT extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void findOneByEmail() {
        User u = User.build("niko@nava.de", "topsecret", Role.ADMIN);
        userRepository.save(u);

        //List<User> users = userRepository.findAll();
        Optional<User> fetched = userRepository.findOneByEmail("niko@nava.de");
        assertTrue(fetched.isPresent());
        System.out.println("---<" + u.getPasswordHash());
        assertThat(fetched.get(), is(u));
    }

}
