package name.xu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import name.xu.entity.enums.HttpContentType;
import name.xu.entity.enums.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ThymeleafController
 *
 * @author huoxu
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    /**
     * index
     */
    @RequestMapping("/index")
    public String index(Model model) {
        List<Map> httpMessages = new ArrayList<>();
        for (HttpContentType httpContentType : HttpContentType.values()) {
            HashMap<String, String> httpMessage = new HashMap<>(2);
            httpMessage.put("contentType", httpContentType.getValue());
            for (HttpMethod httpMethod : HttpMethod.values()) {
            httpMessage.put("httpMethod", httpMethod.getValue());

            }



            httpMessages.add(httpMessage);
        }

        //提交的类型
        model.addAttribute("HttpContentType", httpMessages);
        return "/thymeleaf/index";
    }
    /**
     * hello
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Dear");
        return "/thymeleaf/hello";
    }

    /**
     * 测试Thymeleaf
     */
    @RequestMapping("/dataType")
    public String dataType(Model model) {
        model.addAttribute("name", "Dear");
        return "/thymeleaf/dataType";
    }
}
