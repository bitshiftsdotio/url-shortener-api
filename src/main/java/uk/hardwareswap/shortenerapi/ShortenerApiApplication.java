package uk.hardwareswap.shortenerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "uk.hardwareswap.shortenerapi")
@EntityScan("uk.hardwareswap.shortenerapi")
public class ShortenerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortenerApiApplication.class, args);
    }

}
