package spe.booker.booker;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import spe_booker.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
import spe_booker.Services.UserService;
import spe_booker.models.Booking;
import spe_booker.models.Room;
import spe_booker.models.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpeBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
//@ActiveProfiles("test")
//@Transactional
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


    // Test that controllers, services and repositories are created correctly.
    @Test
    public void CreationTests() throws Exception {
        assertThat(BController).isNotNull();
        assertThat(CController).isNotNull();
        assertThat(RController).isNotNull();
        assertThat(SController).isNotNull();
        assertThat(UController).isNotNull();
        assertThat(WController).isNotNull();

        assertThat(bookingRepository).isNotNull();
        assertThat(roomRepository).isNotNull();
        assertThat(userRepository).isNotNull();

        assertThat(userService).isNotNull();
        assertThat(roomService).isNotNull();
        assertThat(bookingService).isNotNull();


    }


    // Test that database integration works and the database information is accessible by repositories.
    @Test
    @DatabaseSetup("/booker_test.xml")
    public void DB_test() {

        System.out.println(userRepository.count());
        System.out.println(roomRepository.count());
        System.out.println(bookingRepository.count());

        Assert.assertTrue(userRepository.count()== 1);

        Assert.assertTrue(bookingRepository.count()== 1);

        Assert.assertTrue(roomRepository.count() == 1);

    }


    // Test for booking repository and that bookings are created and stored correctly and same with rooms
    @Test
    public void CreateEntitiesTest() {
        User user = new User();
        user.setName("aidan");
        user.setEnabled(1);
        userRepository.save(user);

        Date date = new Date();


        Room room = new Room();
        room.setRoomNo("108");
        room.setBuilding("test8");
        room.setCapacity(10);
        roomRepository.save(room);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setStartDateTime(date);
        booking.setEndDateTime(date);
        booking.setRoom(room);
        bookingRepository.save(booking);



        System.out.println(bookingRepository.count());

    // 2 because our normal code creates 1 other booking somewhere
        Assert.assertTrue(bookingRepository.count()== 2);


        Optional<Room> y = roomRepository.findByRoomNoAndBuilding("108", "test8");

            assertEquals(room.getRoomNo(), y.get().getRoomNo());


        User z = userRepository.findByName("aidan");

            assertEquals(user.getName(), z.getName());


    }


    // Test that User creation and user repository is working fine.
    @Test
    public void DeleteUserTest() {
        User user = new User();
        user.setName("zack");
        userRepository.save(user);

        User z = userRepository.findByName("zack");

        assertEquals(user.getName(), z.getName());

        userService.deleteUser(userRepository.findByName("zack"));

        assert(userRepository.findByName("zack") == null);


    }



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    // Test that static HTML pages are served correctly
    @Test
    public void StaticHTMLTests() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Go To Login");

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Remember me");


    }

    // Authentication tests where we check that a user can perform secured tasks once logged in.
    @Test
    public void AuthenticationTests() throws Exception {

        ResponseEntity<String> result1 = restTemplate.withBasicAuth("user@bristol.ac.uk", "test")
                .getForEntity("/booking/add", String.class);

        ResponseEntity<String> result2 = restTemplate.withBasicAuth("user@bristol.ac.uk", "test")
                .getForEntity("/bookings", String.class);

        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals(HttpStatus.OK, result2.getStatusCode());

    }


}
