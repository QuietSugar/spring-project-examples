package name.xu.config;


import name.xu.webSocket.AuthChannelInterceptor;
import name.xu.webSocket.PrincipalHandshakeHandler;
import name.xu.webSocket.XuHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.Resource;

/**
 * 配置WebSocket消息代理端点，即 stomp 服务端
 * 为了连接安全，setAllowedOrigins设置的允许连接的源地址
 * 如果在非这个配置的地址下发起连接会报403
 * 进一步还可以使用addInterceptors设置拦截器，来做相关的鉴权操作
 * 通过EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理(message broker)的消息,
 * 此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
 *
 * @author Created by Xu
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Resource
    private AuthChannelInterceptor authChannelInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问， 允许跨域
        // 执行顺序 XuHandshakeInterceptor  >   PrincipalHandshakeHandler   >   AuthChannelInterceptor
        registry.addEndpoint("/websocket/server")
                .addInterceptors(new XuHandshakeInterceptor())
                .setHandshakeHandler((new PrincipalHandshakeHandler()))
                // 允许跨域
                .setAllowedOrigins("*")
                // 兼容SockJS
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //启用 SimpleBroker 用户订阅主题的前缀  /topic 代表发布广播，即群发 (queue 一般用于发送私有消息)
        //registry.enableSimpleBroker("/topic/", "/queue/");
        // 用于过滤目标地址
        //registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 客户端 Channel 出入的
     * 拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authChannelInterceptor);
    }
}
