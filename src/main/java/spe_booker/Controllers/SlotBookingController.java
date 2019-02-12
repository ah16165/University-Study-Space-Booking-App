package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spe_booker.Repositorys.SlotBookingRepository;
import spe_booker.Repositorys.SlotRepository;
import spe_booker.models.SlotBooking;
import spe_booker.models.User;
import java.security.Principal;
import java.util.Optional;

@Controller
public class SlotBookingController {

    private static final Logger LOG = LoggerFactory.getLogger(SlotBookingController.class);

    @Autowired
    private spe_booker.Repositorys.UserRepository userRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private
    SlotBookingRepository slotBookingRepository;

    @GetMapping("/slotbooking/add")
    public String addSlotBooking(Model model) {
        model.addAttribute("slotbooking", new SlotBooking());
        model.addAttribute("slots", slotRepository.findAll());
        return "slotbooking_form";
    }

    @PostMapping("/slotbooking")
    public String submitSlotBooking(@ModelAttribute SlotBooking slotbooking, Principal principal, Model model) {

        User user = userRepository.findByName(principal.getName());
        slotbooking.setUser(user);
        SlotBooking slotBooking = slotBookingRepository.save(slotbooking);

        model.addAttribute("slotbooking", slotbooking);
        return "redirect:/slotbooking/" + slotBooking.getId();

    }

    @GetMapping("/slotbooking/{id}")
    public String viewSlotBooking(@PathVariable Long id, Model model) {
        Optional<SlotBooking> slotBooking = slotBookingRepository.findById(id);
        slotBooking.ifPresent(slot1 -> model.addAttribute("slotbooking", slot1));

        return "slotbooking_view";
    }



}
