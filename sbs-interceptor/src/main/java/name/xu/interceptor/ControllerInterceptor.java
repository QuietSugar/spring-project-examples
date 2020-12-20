package name.xu.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * controller 拦截器
 *
 * @author huoxu
 */
@Slf4j
@Aspect
@Component
public class ControllerInterceptor {

    /**
     * 定义拦截规则：
     * 拦截 name.xu.controller.SystemController 包下面的所有类中
     * 并且有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* name.xu.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void log() {
    }

    private static final ObjectMapper mapper = new ObjectMapper();


    @Around("log()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        // 接收到请求，记录请求内容
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String contentType = request.getContentType();
        String params = null;
        if ("application/json".equals(contentType)) {
            params = new String(getRequestPostBytes(request), StandardCharsets.UTF_8);
        } else {
            Map<String, String> paramMap = new HashMap<>();
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        paramMap.put(paramName, paramValue);
                    }
                }
            }
            params = paramMap.toString();
        }

        Object result = null;
        try {
            String ip = request.getHeader("X-Real-IP");
            if (ip == null) {
                ip = request.getRemoteHost();
            }
            log.info("request url: {}, params: {}, ip: {}", request.getRequestURI(), params, ip);
            result = point.proceed();
        } catch (Exception e) {
            log.error("ERROR: ", e);
            //result = ResultUtil.getResult(ResultEnum.SERVER_ERROR);
        }

        String json = mapper.writeValueAsString(result);
        if (json.length() > 5000) {
            json = json.substring(0, 5000);
        }
        log.info("request url: {}, response: {}, TIME: {}ms", request.getRequestURI(), json, System.currentTimeMillis() - start);
        return result;
    }

    private byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }


//	@Before("log()")
//    public void doBefore(JoinPoint point) throws Throwable {
//        startTime.set(System.currentTimeMillis());
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//		Map<String, String> params = new HashMap<String, String>();
//		Enumeration<String> paramNames = request.getParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String paramName = paramNames.nextElement();
//			String[] paramValues = request.getParameterValues(paramName);
//			if (paramValues.length == 1) {
//				String paramValue = paramValues[0];
//				if (paramValue.length() != 0) {
//					params.put(paramName, paramValue);
//				}
//			}
//		}
//
//		log.info("request url: {}, params: {}", request.getRequestURI(), params);
//    }
//
//	@AfterReturning(returning = "ret", pointcut = "log()")
//    public void doAfterReturning(Object ret) throws Throwable {
//		log.info("response: {}, TIME: {}ms", JSONObject.toJSONString(ret), System.currentTimeMillis() - startTime.get());
//    }
}
