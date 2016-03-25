package de.nava.demo.repository;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import de.nava.demo.domain.User;
import de.nava.demo.testsupport.IntegrationTestBase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertTrue;

public class UserRepositoryIT extends IntegrationTestBase {

    //@Autowired
    //private ApplicationContext applicationContext;

    //@Rule
    //public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("spring-demo-test");

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
