package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spe_booker.Repositorys.BookingRepository;
import spe_booker.models.Booking;

import java.util.Collections;
import java.util.Optional;

@Controller
public class BookingController {

    private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/booking/add")
    public String addBooking(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form";
    }

    @PostMapping("/booking")
    public String submitBooking(@ModelAttribute Booking booking) {
        LOG.info("Saving new booking with booking id " + booking.getId());
        Booking booking1 = bookingRepository.save(booking);
        return "redirect:/booking/" + booking1.getId();
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
}
