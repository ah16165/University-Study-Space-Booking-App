package spe_booker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${initial_admin_account.password}")
    private String password;
    @Value("${initial_admin_account.username}")
    private String username;
    @Value("${initial_admin_account.faculty}")
    private String faculty;
    @Value("${initial_admin_account.year}")
    private String year;

    public void run(ApplicationArguments args) {

        if (userRepository.findByName(username) == null) {
            LOG.debug("creating initial admin account");
            userService.createUser(username, "ROLE_ADMIN", password, faculty, year);
        }
        if (userRepository.findByName("user") == null) {
            LOG.debug("creating test user account");
            userService.createUser("user", "user@bristol.ac.uk", "test", "engineering", "2");
        }

    }



}
