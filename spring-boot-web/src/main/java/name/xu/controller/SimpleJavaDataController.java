package name.xu.controller;


import com.alibaba.fastjson.JSON;
import name.xu.pojo.common.SimpleJavaData;
import name.xu.pojo.common.SimplestJavaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 数据类型的测试
 * 使用各种注解接收
 * Controller
 *
 * 以下注解的区别
 *
 *  键值对:    意味着可以接收到URL后面拼接的参数,以及以默认 contentType 提交的参数
 *  可以使用以下注解
 *  RequestParam    表示接收一个键值对,放在key的前面
 *  ModelAttribute  与上面类似,但是表示的是多个key组成的对象
 *
 *  对象接收:   接收json,xml等比较复杂的对象
 *  可以使用以下注解
 *  RequestBody
 *
 * @author huoxu
 */
@Controller
@ResponseBody
@RequestMapping(value = "/data", method = {RequestMethod.POST, RequestMethod.GET})
public class SimpleJavaDataController {
    private static final Logger log = LoggerFactory.getLogger(SimpleJavaDataController.class);

    /**
     * 扁平对象
     * 使用 ModelAttribute 注解接收
     */
    @RequestMapping(value = "/flat/ma")
    public SimplestJavaData sjdma(@ModelAttribute SimplestJavaData simplestJavaData) {
        log.info("接收到数据:{}", JSON.toJSONString(simplestJavaData));
        return simplestJavaData;
    }

    /**
     * 扁平对象
     * 使用 RequestParam 注解接收
     */
    @RequestMapping(value = "/flat/rp")
    public SimplestJavaData sjdRp(
            @RequestParam int intType,
            @RequestParam float floatType,
            @RequestParam boolean booleanType,
            @RequestParam String stringType
    ) {
        SimplestJavaData simplestJavaData = new SimplestJavaData();
        simplestJavaData.setIntType(intType);
        simplestJavaData.setFloatType(floatType);
        simplestJavaData.setBooleanType(booleanType);
        simplestJavaData.setStringType(stringType);
        log.info("接收到数据:{}", JSON.toJSONString(simplestJavaData));
        return simplestJavaData;
    }


    /**
     * 复合对象
     * 使用 RequestBody 注解接收
     */
    @RequestMapping(value = "/complex/rb")
    public SimpleJavaData rb(@RequestBody SimpleJavaData simpleJavaData) {
        log.info("接收到数据:{}", JSON.toJSONString(simpleJavaData));
        return simpleJavaData;
    }
}
