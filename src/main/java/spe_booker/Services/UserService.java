package spe_booker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.UserRepository;
import spe_booker.models.User;


@Service
public class UserService {


    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return findByUsername(username);
    }

    public User findByUsername(String email) {
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
        saveUser(s);

        return s;
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



}
