package name.xu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main
 *
 * @author Created by xu
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "name.xu.mapper")
@EntityScan("name.xu.entity")
@ServletComponentScan("name.xu.interceptor")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
