package name.xu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @author Created by Xu
 */
@Controller
@Slf4j
public class WbController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 接收消息
     * 不带用户信息
     * 不回复
     *
     * @param message message
     */
    @MessageMapping(value = "/message")
    public void message(@Payload String message) {
        log.info("receive : {}", message);
    }

    /**
     * 公开的广播,所有的用户都会接收到
     */
    private static final String PUBLIC_TOPIC = "/topic/public";

    /**
     * 接收消息 并回复
     * 使用 @SendTo 注解回复消息
     */
    @MessageMapping(value = "/messageAndReplyByAnnotations")
    @SendTo(PUBLIC_TOPIC)
    public String messageAndReplyByAnnotations(@Payload String message, @Headers Map map) {
        log.info("receive : {}", message);
        log.info("map : {}", map);
        return message + ":reply";
    }

    /**
     * 接收消息 并回复
     * 使用 SimpMessagingTemplate 手动回复消息
     */
    @MessageMapping(value = "/messageAndReplyByTemplate")
    public void messageAndReplyByTemplate(@Payload String message) {
        log.info("receive : {}", message);
        simpMessagingTemplate.convertAndSend(PUBLIC_TOPIC, message + ":reply");
    }


    /**
     *
     * 订阅的时候的处理逻辑
     */
    @SubscribeMapping(PUBLIC_TOPIC)
    public String subscribe() {
        return "已订阅 : [" + PUBLIC_TOPIC + "]";
    }


    /**
     * 精准推送
     *
     * @param msg
     * @param principal
     * @return
     */
    @MessageMapping("handle1")
    @SendToUser(value = "/topic/greetings1", broadcast = false)
    public String handle1(String msg, Principal principal) {

        return "精准推送，只推送到" + principal.getName();
    }


    /**
     * 广播推送
     *
     * @param msg
     * @param principal
     * @return
     */
    @MessageMapping("handle2")
    @SendTo("topic/greetings2")
    public String handle2(String msg, Principal principal) {

        return "广播推送，所有用户都收得到";
    }

    //@MessageMapping("/sendTest")
    //@SendTo("/topic/subscribeTest")
    //public ServerMessage sendDemo(ClientMessage message) {
    //    logger.info("接收到了信息" + message.getName());
    //    return new ServerMessage("你发送的消息为:" + message.getName());
    //}

}
