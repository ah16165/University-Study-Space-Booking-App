package spe_booker.Services;

import org.springframework.stereotype.Service;
import spe_booker.models.Room;
import spe_booker.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    public List<User> getRoomsAndNoOfBookings(){
        List<User> roomsAndNoOfBookings = new ArrayList<>();

        return roomsAndNoOfBookings;
    }
}
