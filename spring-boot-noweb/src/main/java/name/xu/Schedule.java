package name.xu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */
@Slf4j
@Component
public class Schedule {

    @Value("${project.name}")
    private String projectName;

    @PostConstruct
    private void init() {
        log.debug("Schedule init ...");
        log.info("系统名称: :{}", projectName);

    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void schedule() {
        log.info("当前时间: {}", new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss").format(new Date()));
    }
}
