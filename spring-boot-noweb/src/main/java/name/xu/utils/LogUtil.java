package name.xu.utils;

/**
 * @author Created by HuoXu
 */

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {
    public static void getLogLevel()   {
        if (log.isDebugEnabled()) {
            log.debug("日志级别: DEBUG");
        } else if (log.isInfoEnabled()) {
            log.info("日志级别: Info");
        }
        if (log.isWarnEnabled()) {
            log.warn("日志级别: Warn");
        }
        if (log.isErrorEnabled()) {
            log.error("日志级别: Error");
        }
    }
}
