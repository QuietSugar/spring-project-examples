package name.xu.controller;


import com.alibaba.fastjson.JSON;
import name.xu.entity.Area;
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
public class RequestParamController {
    private static final Logger log = LoggerFactory.getLogger(RequestParamController.class);

    /**
     * 扁平对象
     * 使用 ModelAttribute 注解接收
     */
    @RequestMapping(value = "/flat/ma")
    public Area sjdma(@ModelAttribute Area area) {
        log.info("接收到数据:{}", JSON.toJSONString(area));
        return area;
    }

    /**
     * 扁平对象
     * 使用 RequestParam 注解接收
     */
    @RequestMapping(value = "/flat/rp")
    public Area sjdRp(
            @RequestParam int id,
            @RequestParam String name
    ) {
        Area area = new Area();
        area.setId(id);
        area.setName(name);
        log.info("接收到数据:{}", JSON.toJSONString(area));
        return area;
    }


    /**
     * 复合对象
     * 使用 RequestBody 注解接收
     */
    @RequestMapping(value = "/complex/rb")
    public Area rb(@RequestBody Area area) {
        log.info("接收到数据:{}", JSON.toJSONString(area));
        return area;
    }
}
