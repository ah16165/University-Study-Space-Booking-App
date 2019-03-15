package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.RoomService;
import spe_booker.models.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BookingController {

    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @GetMapping("/booking/add")
    public String addBooking(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());
        return "booking_form";
    }

    @GetMapping("/makebooking")
    public String makeBooking(Model model){
        model.addAttribute("bookingRequest",new BookingRequest() );
        return "makebooking";
    }

    @PostMapping("/makebooking")
    public String submitDateTime(@ModelAttribute BookingRequest bookingRequest, Model model){
        model.addAttribute("bookingRequest", bookingRequest);
        return "makebookingRoom";
    }

    @GetMapping(value = {"/makebookingRoom"})
    public String makebookingRoom(@ModelAttribute BookingRequest bookingRequest, Model model) {
        
        model.addAttribute("bookingRequestDateAndLength", bookingRequest);
        model.addAttribute("rooms", roomRepository.findAll());
        return "makebookingRoom";
    }

    User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username);
    }

    @PostMapping("/booking")
    public String submitBooking(@ModelAttribute BookingRequest bookingRequest) {
        Booking booking = new Booking();
        Optional<Room> room = roomService.findByRoomNoAndBuilding(bookingRequest.getRoomNo(), bookingRequest.getBuilding());
        booking.setDateTime(bookingRequest.getDateTime());
        booking.setLength(bookingRequest.getLength());
        booking.setId(bookingRequest.getId());
        booking.setUser(getCurrentUser());
        if (room.isPresent()) {
            System.out.print("####Room IS present!");
            booking.setRoom(room.get());
            LOG.info("Saving new booking with booking id " + booking.getId());
            Booking booking1 = bookingRepository.save(booking);
            return "redirect:/booking/" + booking1.getId();
        } else {
            System.out.print("####Room not present!");
            return "/error/error";
        }
    }

    @GetMapping(value = {"/booking", "/booking/{id}"})
    public String viewBooking(@PathVariable Optional<Long> id, Model model) {
        LOG.info("Listing bookings");
        model.addAttribute("bookings", id.map(aLong -> Collections.singletonList(bookingRepository.findById(aLong).get()))
                .orElseGet(() -> bookingRepository.findAll()));
        return "booking_view";
    }


    @GetMapping(value = {"/viewbookings"})
    public String viewBooking(Model model) {
        LOG.info("Listing bookings for viewbookings");
        List<Booking> currentUserBookings = bookingRepository.findBookingsByUser(getCurrentUser());
        model.addAttribute("bookings", currentUserBookings);
        return "viewbookings";
    }


    //Called automatically when building the model
    //Formats the date/time
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
    }

}
