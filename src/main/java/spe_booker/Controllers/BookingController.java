package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.Repositorys.RoomRepository;
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


    @GetMapping(value = {"/bookings"})
    public String viewBookings(Model model) {
        LOG.info("Listing bookings for viewbookings");
        List<Booking> currentUserBookings = bookingRepository.findBookingsByUser(userService.getCurrentUser());
        model.addAttribute("bookings", currentUserBookings);
        return "view_bookings";
    }


    @GetMapping(value = {"/user/{username}/bookings"})
    public String viewBookingsForuser(@PathVariable String username, Model model) {
        LOG.info("Listing bookings for a specific user\n");
        List<Booking> currentUserBookings = bookingRepository.findBookingsByUser(userService.findByUsername(username));
        model.addAttribute("bookings", currentUserBookings);
        return "view_bookings";
    }


    @GetMapping(value = {"/booking/{id}"})
    public String viewbooking(@PathVariable Long id, Model model) {
        LOG.info("Listing details for a single booking");
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            model.addAttribute("booking", booking.get());
            return "view_booking";
        } else {
            System.out.print("Booking not present!");
            return "/error/error-400";
        }
    }

    @GetMapping("/booking/add2")
    public String makeBooking(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());
        return "make_booking";
    }

    @PostMapping("/booking/add2")
    public String submitDateTime(@ModelAttribute BookingRequest bookingRequest, RedirectAttributes redirectAttributes) {
        System.out.print("########1 - " + bookingRequest.getDateTime() + "#####\n");
        redirectAttributes.addFlashAttribute("bookingRequest", bookingRequest);
        return "redirect:/booking/add2/room";
    }

    @GetMapping(value = {"/booking/add2/room"})
    public String makebookingRoom(@ModelAttribute("bookingRequest") final BookingRequest bookingRequestDateAndLength, Model model) {
        System.out.print("########2 - " + bookingRequestDateAndLength.getDateTime());
        model.addAttribute("bookingRequestDateAndLength", bookingRequestDateAndLength);
        model.addAttribute("rooms", roomRepository.findAll());
        return "make_booking_room";
    }


    @GetMapping("/booking/add")
    public String addBooking(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());
        return "booking_form";
    }

    @PostMapping("/booking")
    public String submitBooking(@ModelAttribute BookingRequest bookingRequest) {

        User user = userService.getCurrentUser();
        if (user.getBlacklisted()){
            System.out.print("Blacklisted user attempted to make booking, but was blocked.");
            return "/error/error";
        } else {
            Optional<Room> room = roomService.findByRoomNoAndBuilding(bookingRequest.getRoomNo(), bookingRequest.getBuilding());
            if (room.isPresent()){
                Booking booking = new Booking();
                booking.setUser(user);
                booking.setDateTime(bookingRequest.getDateTime());
                booking.setLength(bookingRequest.getLength());
                booking.setId(bookingRequest.getId());
                booking.setRoom(room.get());
                LOG.info("Saving new booking with booking id " + booking.getId());
                Booking booking1 = bookingRepository.save(booking);
                return "redirect:/booking/" + booking1.getId();
            } else {
                System.out.print("Room not found for booking creation.\n");
                return "/error/error-400";
            }
        }
    }


    @PostMapping(value = {"/booking/delete/{id}"})
    public String deleteBooking(@PathVariable Long id){
        LOG.info("Deleting booking: "+ id+ "\n");
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            bookingRepository.deleteById(id);
            return "view_bookings";
        } else {
            System.out.print("####Booking not present!");
            return "/error/error";
        }
    }

    //Called automatically when building the model
    //Formats the date/time
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
    }

}
