package name.xu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import name.xu.service.SystemService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class SystemController {
    @Autowired
    SystemService testService;

    @RequestMapping("/header")
    public Object header(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    @RequestMapping("/add")
    public Object add() throws JsonProcessingException {
        //Test test = new Test();
        //test.setFirstName(RandomStringUtils.randomAlphanumeric(5));
        //test.setLastName(RandomStringUtils.randomAlphanumeric(8));
        //int id = testService.insert(test);
        //Result result = new Result();
        //ObjectMapper mapper = new ObjectMapper();
        //result.setResult(mapper.writeValueAsString(test));
        //return result;
        return null;
    }

    @RequestMapping("/list")
    public Object list() {
        return testService.list();
    }

    @RequestMapping("/info")
    public Object info() {
        //getConfig();
        //Config();
        //all();
        return null;
    }
}
