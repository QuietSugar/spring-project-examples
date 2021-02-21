package name.xu.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Created by Xu
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Slf4j
public class AuthChannelInterceptor implements ChannelInterceptor {
    private static Map<String, XuPrincipal> userMap = new HashMap<>();

    static {
        XuPrincipal xuPrincipal = new XuPrincipal("root", "123456");
        userMap.put("root", xuPrincipal);
    }


    @Override
    public boolean preReceive(MessageChannel channel) {
        log.info("=============================preReceive : {}", channel);
        return true;
    }


    /**
     * 连接前监听
     *
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("=============================preSend : {}", message);

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        //1、判断是否首次连接
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            //2、判断token
            String name = accessor.getFirstNativeHeader("name");
            String password = accessor.getFirstNativeHeader("password");
            if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
                XuPrincipal xuPrincipal = userMap.get(name);
                if (xuPrincipal != null) {
                    //如果存在用户信息，将用户名赋值，后期发送时，可以指定用户名即可发送到对应用户
                    accessor.setUser(xuPrincipal);
                    return message;
                } else {
                    log.warn("用户不存在");
                }
            } else {
                log.warn("请输入用户名和密码");
                //return null;
            }
        }
        //不是首次连接，已经登陆成功
        return message;
    }
}
