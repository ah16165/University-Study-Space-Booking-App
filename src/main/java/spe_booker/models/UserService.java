package spe_booker.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spe_booker.Repositorys.UserRepository;



@Service
public class UserService {


    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
