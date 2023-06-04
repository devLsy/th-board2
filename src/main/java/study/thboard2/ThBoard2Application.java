package study.thboard2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ThBoard2Application {

    public static void main(String[] args) {
        SpringApplication.run(ThBoard2Application.class, args);
    }

}
