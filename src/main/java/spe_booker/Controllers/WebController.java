package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.User;
import spe_booker.models.UserService;
import org.springframework.ui.Model;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login");
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @RequestMapping("/home")
    public String home(Model model) {
        User user = userService.getCurrentUser();
        if (user.getBlacklisted()) {
            model.addAttribute("blacklisted", true);
        } else {
            model.addAttribute("blacklisted", false);
        }
        return "home";
    }

    @RequestMapping("/statistics")
    public String statistics(Model model){
        if (userService.getCurrentUser().isAdmin()) {
            return "view_statistics";
        } else {
            System.out.print("Non admin user attempted to access statistics\n");
            return "/error/error";
        }
    }



}