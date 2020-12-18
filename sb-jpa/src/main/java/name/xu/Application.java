package name.xu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/5/26
 */
@SpringBootApplication
@EnableScheduling
@EntityScan("name.xu.entity")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
