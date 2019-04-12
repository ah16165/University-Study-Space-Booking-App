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
import spe_booker.Repositorys.UserRepository;
import spe_booker.Services.UserService;
import spe_booker.models.User;

import java.util.Optional;

@Controller
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    @GetMapping("/user/add")
//    public String addRoom(Model model) {
//        model.addAttribute("user", new User());
//        return "user_form";
//    }

    @PostMapping("/user")
    public String submitRoom(@ModelAttribute User user) {
        LOG.info("Saving new user with user id " + user.getId());
        User user1 = userService.save(user);
        return "redirect:/user/" + user1.getId();
    }

    @GetMapping(value = {"/users"})
    public  String viewAllUsers(Model model){
        LOG.info("Listing users");
        model.addAttribute("users", userService.findAll());
        return "view_users";
    }


    @GetMapping(value = {"/user/{id}"})
    public String viewbooking(@PathVariable Long id, Model model) {
        LOG.info("Listing details for a single user");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            model.addAttribute("user", user.get());
            return "view_user";
        } else {
            LOG.info("User not present!");
            return "/error/error";
        }
    }

    @PostMapping(value = {"/user/delete/{id}"})
    public String deleteUser(@PathVariable Long id){
        LOG.info("Deleting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            userService.deleteUser(user.get());
            return "user_deleted";
        } else {
            System.out.print("####User not present!");
            return "/error/error";
        }
    }


    @PostMapping(value = {"/user/blacklist/{id}"})
    public String blacklistUser(@PathVariable Long id){
        LOG.info("Blacklisting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            user.get().setBlacklisted(true);
            userService.save(user.get());
            return "user_blacklisted";
        } else {
            System.out.print("####User not present!");
            return "/error/error";
        }
    }

    @PostMapping(value = {"/user/unblacklist/{id}"})
    public String unblacklistUser(@PathVariable Long id){
        LOG.info("Unblacklisting user: "+ id+ "\n");
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            user.get().setBlacklisted(false);
            userService.save(user.get());
            return "user_unblacklisted";
        } else {
            System.out.print("####User not present!");
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
        user.setBlacklisted(false);
        user.setRole("student");
        user.setEnabled(1);
        userService.save(user);
        return "user_added";
    }

}
