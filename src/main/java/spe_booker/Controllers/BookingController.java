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
import spe_booker.Services.BookingService;
import spe_booker.Services.RoomService;
import spe_booker.Services.UserService;
import spe_booker.models.Booking;
import spe_booker.models.BookingRequest;
import spe_booker.models.Room;
import spe_booker.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;


    @GetMapping(value = {"/bookings"})
    public String viewBookings(Model model) {
        LOG.info("Listing bookings for viewbookings");
        List<Booking> currentUserBookings = bookingService.findBookingsByUser(userService.getCurrentUser());
        model.addAttribute("bookings", currentUserBookings);
        return "view_bookings";
    }


    @GetMapping(value = {"/user/{username}/bookings"})
    public String viewBookingsForuser(@PathVariable String username, Model model) {
        LOG.info("Listing bookings for a specific user\n");
        List<Booking> currentUserBookings = bookingService.findBookingsByUser(userService.findByUsername(username));
        model.addAttribute("bookings", currentUserBookings);
        return "view_bookings";
    }


    @GetMapping(value = {"/booking/{id}"})
    public String viewbooking(@PathVariable Long id, Model model) {
        LOG.info("Listing details for a single booking");
        Optional<Booking> booking = bookingService.findById(id);
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
    public String makebookingRoom(@ModelAttribute("bookingRequest") final BookingRequest bookingRequestDateAndDuration, Model model) {
        System.out.print("########2 - " + bookingRequestDateAndDuration.getDateTime());
        model.addAttribute("bookingRequestDateAndDuration", bookingRequestDateAndDuration);
        model.addAttribute("rooms", roomRepository.findByDateTimeLength(bookingRequestDateAndDuration.getDateTime(), bookingRequestDateAndDuration.getDuration()));
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
                Booking booking1 = bookingService.createBookingFromBookingRequest(bookingRequest, user, room.get());
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
        Optional<Booking> booking = bookingService.findById(id);
        if (booking.isPresent()){
            bookingService.deleteById(id);
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
