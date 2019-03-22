package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spe_booker.Repositorys.RoomRepository;
import spe_booker.Services.RoomService;
import spe_booker.Services.UserService;

@Controller
public class StatisticsController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);


    @RequestMapping("/statistics")
    public String statistics(Model model){
        LOG.info("Showing statistics page");

        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("top10Users", userService.findTop10ByNumberOfBookings());
        return "view_statistics";
    }
}
