package name.xu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import name.xu.AreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class AreaController {
    @Resource
    AreaService areaService;

    @RequestMapping("/header")
    public Object header(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    @RequestMapping("/list")
    public Object list() {
        return areaService.list();
    }
}
