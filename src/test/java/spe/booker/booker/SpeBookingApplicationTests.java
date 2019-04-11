package spe.booker.booker;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;
import spe_booker.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import spe_booker.Controllers.*;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.UserRepository;
import spe_booker.Services.BookingService;
import spe_booker.Services.RoomService;
import spe_booker.Services.StatisticsService;
import spe_booker.Services.UserService;
import spe_booker.models.Booking;
import spe_booker.models.Room;
import spe_booker.models.User;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpeBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
@Transactional
public class SpeBookingApplicationTests {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;




    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private StatisticsService statService;

    @Autowired
    private BookingService bookingService;



    @Autowired
    private BookingController BController;

    @Autowired
    private CustomErrorController CController;

    @Autowired
    private RoomController RController;

    @Autowired
    private StatisticsController SController;

    @Autowired
    private UserController UController;

    @Autowired
    private WebController WController;


    @Test
    public void CreationTests() throws Exception {
        assertThat(BController).isNotNull();
        assertThat(CController).isNotNull();
        assertThat(RController).isNotNull();
        assertThat(SController).isNotNull();
        assertThat(UController).isNotNull();
        assertThat(WController).isNotNull();

        assertThat( bookingRepository).isNotNull();
        assertThat(roomRepository).isNotNull();
        assertThat(userRepository).isNotNull();

        assertThat(userService).isNotNull();
        assertThat(roomService).isNotNull();
        assertThat(statService).isNotNull();
        assertThat(bookingService).isNotNull();


    }



    @Test
    @DatabaseSetup("booker_test.xml")
    public void DB_test() {

        Assert.assertTrue(userRepository.count()> 0);
        Assert.assertTrue(roomRepository.count() > 0);

    }


    @Test
    public void CreateBookingTest() {
        User user = new User();
        user.setId(1L);
        userRepository.save(user);

        Date date = new Date();

        Room room = new Room();
        room.setRoomNo("100");
        room.setBuilding("test");
        room.setCapacity(10);
        roomRepository.save(room);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setStartDateTime(date);
        booking.setEndDateTime(date);
        booking.setRoom(room);
        booking.setId(69L);
        bookingRepository.save(booking);

        Optional<Booking> x = bookingRepository.findById(69L);
        if (x.isPresent()) {
            Assert.assertEquals(booking.getId(), x.get().getId());
        }

    }

    @Test
    public void CreateUserTest() {
        User user = new User();
        user.setId(1L);
        userRepository.save(user);

        Optional<User> x = userRepository.findById(1L);
        if (x.isPresent()) {
            Assert.assertEquals(user.getId(), x.get().getId());
        }

    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void HomeAndLoginHTML() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Go To Login");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Remember me");

    }

}
