package spe_booker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.UserRepository;
import spe_booker.Services.BookingService;
import spe_booker.Services.RoomService;
import spe_booker.Services.UserService;


@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;


    private String password = "test";
    private String username = "admin";

    public void run(ApplicationArguments args) {

        if (userRepository.findByName(username) == null) {
            LOG.debug("creating initial admin account");
            userService.createUser(username, "admin@bristol.ac.uk", password, "engineering", "admin",false);
        }
        if (userRepository.findByName("user") == null) {
            LOG.debug("creating test user account");
            userService.createUser("user", "user@bristol.ac.uk", "test", "engineering", "student",false);
        }

    }



}
