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
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Services.RoomService;
import spe_booker.models.Room;

import java.util.Collections;
import java.util.Optional;

@Controller
public class RoomController {

    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @GetMapping("/room/add")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room());
        return "room_form";
    }

    @PostMapping("/room")
    public String submitRoom(@ModelAttribute Room room) {
        LOG.info("Saving new room with room number " + room.getRoomNo());
        Room room1 = roomService.save(room);
        return "redirect:/room/" + room1.getBuilding() + "/" +room1.getRoomNo();
    }

//    @GetMapping(value = {"/room", "/room/{id}"})
//    public String viewRoom(@PathVariable Optional<Long> id, Model model) {
//        LOG.info("Listing rooms");
//        model.addAttribute("rooms", id.map(aLong -> Collections.singletonList(roomRepository.findById(aLong).get()))
//                .orElseGet(() -> roomRepository.findAll()));
//        return "room_view";
//    }

    @GetMapping(value = {"/rooms"})
    public String viewRooms(Model model) {
        LOG.info("Listing rooms");
        model.addAttribute("rooms", roomService.findAll());
        return "view_rooms";
    }

    @GetMapping(value = {"/room/{building}/{roomNo}"})
    public String viewRoom(@PathVariable String building, @PathVariable String roomNo, Model model){
        LOG.info("Displaying info for a single room");
        Optional<Room> room = roomService.findByRoomNoAndBuilding(roomNo, building);
        if (room.isPresent()){
            model.addAttribute("room", room.get());
            return "view_room";
        } else {
            return "/error/error";
        }

    }

}
