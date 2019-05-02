package spe_booker.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.models.Booking;
import spe_booker.models.BookingRequest;
import spe_booker.models.Room;
import spe_booker.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(User user, Date startDateTime, Date endDateTime, Long id, Room room) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setStartDateTime(startDateTime);
        booking.setEndDateTime(endDateTime);
        booking.setId(id);
        booking.setRoom(room);
        Date creationDate = new Date();
        booking.setCreationDate(creationDate);
        System.out.print("Creation Date and Time: "+creationDate+"\n");
        LOG.info("Saving new booking with booking id " + booking.getId());
        saveBooking(booking);
        return booking;
    }

    public Booking createBookingFromBookingRequest(BookingRequest bookingRequest, User user, Room room){
        return createBooking(user, bookingRequest.getStartDateTime(), bookingRequest.getEndDateTime(), bookingRequest.getId(), room);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);

    }

    public List<Booking> findBookingsByUser(User user){
        return bookingRepository.findBookingsByUser(user);
    }

    public List<Booking> findAllFutureBookings(){
        Date currentTime = new Date();
        return bookingRepository.findAllByEndDateTimeAfter(currentTime);
    }

    public List<Booking> findFutureBookingsForUser(User user){
        Date currentTime = new Date();
        return bookingRepository.findAllByEndDateTimeAfterAndUser(currentTime, user);
    }

    public Optional<Booking> findById(Long id){
        return bookingRepository.findById(id);
    }

    public void deleteById(Long id){
        bookingRepository.deleteById(id);
    }

    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    public List<Booking> findAll() { return bookingRepository.findAll(); }

    public void deleteAllByUser(User user){
        bookingRepository.deleteAllByUser(user);
    }

}
