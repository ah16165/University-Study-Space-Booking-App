package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Repositorys.SlotRepository;
import spe_booker.models.Room;
import spe_booker.models.Slot;

import java.util.List;
import java.util.Optional;

@Controller
public class SlotController {

    private static final Logger LOG = LoggerFactory.getLogger(SlotController.class);

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SlotRepository slotRepository;

    @GetMapping("/slot/add")
    public String addSlot(Model model) {
        model.addAttribute("slot", new Slot());
        List<Room> all = roomRepository.findAll();
        assert all.size() > 0;
        model.addAttribute("slots", all);
        return "slot_form";
    }

    @PostMapping("/slot")
    public String submitSlot(@ModelAttribute Slot slot) {
        Slot slot1 = slotRepository.save(slot);
        return "redirect:/slot/" + slot1.getId();

    }

    @GetMapping("/slot/{id}")
    public String viewSlot(@PathVariable Long id, Model model) {
        Optional<Slot> slot = slotRepository.findById(id);
        slot.ifPresent(slot1 -> model.addAttribute("slot", slot1));
        return "slot_view";
    }



}
