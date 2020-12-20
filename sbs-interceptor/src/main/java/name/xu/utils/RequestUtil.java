package name.xu.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by HuoXu
 */
public class RequestUtil {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null) {
            ip = request.getRemoteHost();
        }
        return ip;
    }
}
