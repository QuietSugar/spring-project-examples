package name.xu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${project.name}")
    private String projectName;
    /**
     * 重写 run 方法,主要运行内容
     */
    @Resource
    UserRepository userRepository;

    @Override
    public void run(String... args) {
        log.info("系统名称: :{} start..", projectName);
        Iterable<User> all = userRepository.findAll();
        all.forEach(user -> {
            log.debug("user: {}", user);
        });
    }
}
