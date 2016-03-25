package de.nava.demo;

import de.nava.demo.domain.User;
import de.nava.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class SpringBootSecurityApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

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

        List<User> users = userRepository.findAll();
        System.out.println("-->" + users);

        Optional<User> fetched = userRepository.findOneByEmail("niko@nava.de");
        assertTrue(fetched.isPresent());
    }

}
