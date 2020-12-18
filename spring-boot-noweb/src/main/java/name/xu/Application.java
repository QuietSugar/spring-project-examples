package name.xu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import name.xu.utils.LogUtil;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 重写 run 方法,主要运行内容
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("spring-boot-noweb start..");
        LogUtil.getLogLevel();
    }
}
