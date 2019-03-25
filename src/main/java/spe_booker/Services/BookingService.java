package spe_booker.Services;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.Booking;
import spe_booker.models.Room;
import spe_booker.models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BookingService {


    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Booking createBooking(Long length, Date date, User user, Room room) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date creationDate = new Date();

        System.out.print("Booking Date: "+date+"\n");

        Booking b = new Booking();
        b.setDuration(length);
        b.setDateTime(date);
        b.setUser(user);
        b.setRoom(room);
        b.setCreationDate(creationDate);
        saveBooking(b);
        return b;
    }

    public Booking saveBooking(Booking booking) {

        return bookingRepository.save(booking);

    }

//    public List<Booking> getBookingsInLastWeek(){
//        Date currentDate = new Date();
//        Date lastWeek = new Date();
//        lastWeek.setTime((long) currentDate.getTime()-604800000);
//        return bookingRepository.findBookingsByDateTimeGreaterThanEqual(lastWeek);
//    }

//    public List<Pair<Room, Integer>> getRoomsAndNoOfBookingWeek(){
//        List<Pair<Room, Integer>> roomsAndNoOfBookingWeek = new ArrayList<>();
//        for (Booking booking : getBookingsInLastWeek()){
//            if (roomsAndNoOfBookingWeek.contains())
//        }
//    }

}
