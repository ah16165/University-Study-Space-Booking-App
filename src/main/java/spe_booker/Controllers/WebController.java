package spe_booker.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import spe_booker.Repositorys.UserRepository;
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
        registry.addViewController("/userhome");
        }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }



    }
