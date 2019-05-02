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
import spe_booker.Services.UserService;
import spe_booker.models.User;

import java.util.Optional;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/users"})
    public  String viewAllUsers(Model model){
        LOG.info("Listing users");
        model.addAttribute("users", userService.findAll());
        return "user_view_list";
    }

    @PostMapping(value = {"/user/delete/{id}"})
    public String deleteUser(@PathVariable Long id){
        LOG.info("Deleting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            userService.deleteUser(user.get());
            return "user_deleted";
        } else {
            return "/error/error";
        }
    }

    @PostMapping(value = {"/user/blacklist/{id}"})
    public String blacklistUser(@PathVariable Long id){
        LOG.info("Blacklisting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            user.get().setBlacklisted(true);
            userService.update(user.get());
            return "user_blacklisted";
        } else {
            return "/error/error";
        }
    }

    @PostMapping(value = {"/user/unblacklist/{id}"})
    public String unblacklistUser(@PathVariable Long id){
        LOG.info("Unblacklisting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            user.get().setBlacklisted(false);
            userService.update(user.get());
            return "user_unblacklisted";
        } else {
            return "/error/error";
        }
    }

    @GetMapping("/user/add")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user_add";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user){
        LOG.info("Adding a new user");
        user.setBlacklisted(false);
        user.setRole("student");
        user.setEnabled(1);
        userService.save(user);
        return "user_added";
    }

}
