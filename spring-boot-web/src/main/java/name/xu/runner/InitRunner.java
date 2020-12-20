package name.xu.runner;


import lombok.extern.slf4j.Slf4j;
import name.xu.service.SystemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
//import name.xu.utils.SystemUtil;

import javax.annotation.Resource;

/**
 * 初始化运行的代码
 */
@Slf4j
@Component
public class InitRunner implements ApplicationRunner {

    @Resource
    private SystemService systemService;

    @Value("${env.name}")
    private String envName;

    @Override
    public void run(ApplicationArguments args) {
        //SystemUtil.getConfig();
        //SystemUtil.Config();
        //SystemUtil.all();
        log.info("环境名称: {}", envName);
    }
}
