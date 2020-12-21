package name.xu;

import lombok.extern.slf4j.Slf4j;
import name.xu.entity.Area;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.Resource;
import java.util.List;

/**
 * Main
 *
 * @author Created by xu
 */
@SpringBootApplication
@MapperScan(basePackages = "name.xu.mapper")
@EntityScan("name.xu.entity")
@Slf4j
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    AreaService areaService;

    @Override
    public void run(String... args) throws Exception {
        List<Area> list = areaService.list();
        log.info("data: {}", list);
    }
}
