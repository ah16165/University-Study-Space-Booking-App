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
import spe_booker.models.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Controller
public class BookingController {

    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/booking/add")
    public String addBooking(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());

        return "booking_form";
    }

    @PostMapping("/booking")
    public String submitBooking(@ModelAttribute BookingRequest bookingRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        System.out.print("##########Email = " + username + "########\n" +
                "RoomId = " + bookingRequest.getRoomId() + "###########\n");
        User user = userService.findByUsername(username);
        Booking booking = new Booking();
        Optional<Room> room = roomRepository.findById(bookingRequest.getRoomId());
        booking.setDateTime(bookingRequest.getDateTime());
        booking.setLength(bookingRequest.getLength());
        booking.setId(bookingRequest.getId());
        booking.setUser(user);
        if (room.isPresent()) {
            booking.setRoom(room.get());
            LOG.info("Saving new booking with booking id " + booking.getId());
            Booking booking1 = bookingRepository.save(booking);
            return "redirect:/booking/" + booking1.getId();
        } else {
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
        model.addAttribute("bookings", bookingRepository.findAll());
        return "viewbookings";
    }


    //Called automatically when building the model
    //Formats the date/time
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
    }

}
