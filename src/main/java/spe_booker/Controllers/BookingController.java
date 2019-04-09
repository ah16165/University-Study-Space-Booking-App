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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@SessionAttributes("bookingRequest")
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
        List<Booking> bookings = new ArrayList<>();
        if (userService.getCurrentUser().get().isAdmin()){
            bookings.addAll(bookingService.findAll());
        } else {
            bookings.addAll(bookingService.findBookingsByUser(userService.getCurrentUser().get()));
        }
        model.addAttribute("bookings", bookings);
        return "view_bookings";
    }


    @GetMapping(value = {"/user/{username}/bookings"})
    public String viewBookingsForuser(@PathVariable String username, Model model) {
        LOG.info("Listing bookings for a specific user\n");
        List<Booking> currentUserBookings = bookingService.findBookingsByUser(userService.findByUsername(username).get());
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

    @GetMapping("/booking/add")
    public String makeBooking(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());
        return "make_booking";
    }

    @PostMapping("/booking/add")
    public String submitDateTime(@ModelAttribute BookingRequest bookingRequest, RedirectAttributes redirectAttributes) {
        System.out.print("########1 - " + bookingRequest.getStartDateTime() + "#####\n " + bookingRequest.getDuration() + " \n");
        redirectAttributes.addFlashAttribute("bookingRequest", bookingRequest);
        return "redirect:/booking/add/room";
    }

    @GetMapping(value = {"/booking/add/room"})
    public String makebookingRoom(@ModelAttribute("bookingRequest") BookingRequest bookingRequest, Model model) {
        System.out.print("########2 - " + bookingRequest.getStartDateTime() + " - ####\n");
        model.addAttribute("bookingRequestDateAndDuration", bookingRequest);
        model.addAttribute("rooms", roomService.findAvailable(bookingRequest.getStartDateTime(), bookingRequest.getEndDateTime()));
        return "make_booking_room";
    }

    @PostMapping("/booking/add/room/{building}/{roomNo}")
    public String submitBookingRoom(@ModelAttribute("bookingRequest") BookingRequest bookingRequest, @PathVariable String building, @PathVariable String roomNo, Model model){
        System.out.print("###5 Booking " + bookingRequest.getDuration() + " - " + "\n");
        bookingRequest.setBuilding(building);
        bookingRequest.setRoomNo(roomNo);

        User user = userService.getCurrentUser().get();
        if (user.getBlacklisted()){
            System.out.print("Blacklisted user attempted to make booking, but was blocked.");
            return "/error/error";
        } else {
            Optional<Room> room = roomService.findByRoomNoAndBuilding(roomNo, building);
            if (room.isPresent()){
                Booking booking1 = bookingService.createBookingFromBookingRequest(bookingRequest, user, room.get());
                return "redirect:/booking/confirmed";
            } else {
                System.out.print("Room not found for booking creation.\n");
                return "/error/error-400";
            }
        }
    }

    @GetMapping("/booking/confirmed")
    public String bookingConfirmed(){
        return "booking_confirmed";
    }



    @PostMapping(value = {"/booking/delete/{id}"})
    public String deleteBooking(@PathVariable Long id){
        LOG.info("Deleting booking: "+ id+ "\n");
        Optional<Booking> booking = bookingService.findById(id);
        if (booking.isPresent()){
            bookingService.deleteBooking(booking.get());
            return "redirect:/booking/cancelled";
        } else {
            System.out.print("####Booking not present!");
            return "/error/error";
        }
    }

    @GetMapping("/booking/cancelled")
    public String bookingCancelled(){
        return "booking_cancelled";
    }

    //Called automatically when building the model
    //Formats the date/time
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
    }



}
