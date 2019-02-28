package spe_booker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.UserService;


@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private String password = "123";
    private String username = "admin";

    public void run(ApplicationArguments args) {

        if (userRepository.findByName(username) == null) {
            LOG.debug("creating initial admin account");
            userService.createUser(username, "user@bristol.ac.uk", password, "engineering", "0");
        }
        if (userRepository.findByName("user") == null) {
            LOG.debug("creating test user account");
            userService.createUser("user", "user@bristol.ac.uk", "test", "engineering", "2");
        }

    }



}
