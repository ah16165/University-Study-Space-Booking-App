package spe_booker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${/home/ubuntu}/.secret.properties", ignoreResourceNotFound = true)
})


@EnableJpaRepositories
@SpringBootApplication
public class SpeBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeBookingApplication.class, args);
    }

}
