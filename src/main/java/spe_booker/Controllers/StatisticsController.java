package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StatisticsController {

    private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);


    @RequestMapping("/statistics")
    public String statistics(Model model){
        LOG.info("Showing statistics page");
        return "view_statistics";
    }
}
