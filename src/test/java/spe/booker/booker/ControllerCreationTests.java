package spe.booker.booker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spe_booker.Controllers.*;
import spe_booker.SpeBookingApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpeBookingApplication.class)
public class ControllerCreationTests {


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
    }

}
