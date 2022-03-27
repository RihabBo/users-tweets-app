package wishow.rihab.tweeter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "wishow.rihab.tweeter")
@EntityScan(basePackages = "wishow.rihab.tweeter")
@EnableJpaRepositories(basePackages = "wishow.rihab.tweeter")
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }
}
