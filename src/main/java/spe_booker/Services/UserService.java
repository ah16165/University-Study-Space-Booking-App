package spe_booker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private BookingService bookingService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BookingService bookingService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return findByUsername(username);
    }

    public Optional<User> findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    public User createUser(String name, String email, String password, String faculty, String role, Boolean blacklisted) {
        User s = new User();
        s.setName(name);
        s.setUsername(email);
        s.setPassword(password);
        s.setFaculty(faculty);
        s.setRole(role);
        s.enabled = 1;
        s.setBlacklisted(blacklisted);
        save(s);
        return s;
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findTop10ByNumberOfBookings(){
        List<User> top10ByNumberOfBookings = new ArrayList<>();
        List<User> users = findAll();
        users.sort(Comparator.comparing(User::getNumberOfBookings).reversed());
        for (int i = 0; i < 10 && i < users.size(); i++){
            top10ByNumberOfBookings.add(users.get(i));
        }
        return top10ByNumberOfBookings;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(User user){
        bookingService.deleteAllByUser(user);
        userRepository.delete(user);
    }

}
