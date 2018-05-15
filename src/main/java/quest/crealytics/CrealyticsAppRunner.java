package quest.crealytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@SpringBootApplication
@EnableWebFlux
public class CrealyticsAppRunner {
    public static void main(String[] args) {
        SpringApplication.run(CrealyticsAppRunner.class, args);
    }
}
