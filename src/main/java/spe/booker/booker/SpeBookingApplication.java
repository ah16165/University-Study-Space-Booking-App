package spe.booker.booker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpeBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeBookingApplication.class, args);
    }

    user testUser = new user(1, "at17556@my.bristol.ac.uk", "at17556", "CompSci");
}

