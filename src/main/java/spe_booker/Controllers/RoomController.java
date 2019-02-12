package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.models.Room;

import java.util.Collections;
import java.util.Optional;

@Controller
public class RoomController {

    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/room/add")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room());
        return "room_form";
    }

    @PostMapping("/room")
    public String submitRoom(@ModelAttribute Room room) {
        LOG.info("Saving new room with room number " + room.getRoomNo());
        Room room1 = roomRepository.save(room);
        return "redirect:/room/" + room1.getId();
    }

    @GetMapping(value = {"/room", "/room/{id}"})
    public String viewRoom(@PathVariable Optional<Long> id, Model model) {
        LOG.info("Listing rooms");
        model.addAttribute("rooms", id.map(aLong -> Collections.singletonList(roomRepository.findById(aLong).get()))
                .orElseGet(() -> roomRepository.findAll()));
        return "room_view";
    }

}
