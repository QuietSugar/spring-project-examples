package name.xu.webSocket;

import lombok.extern.slf4j.Slf4j;
import name.xu.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 创建握手 此类用来获取登录用户信息并交由websocket管理
 *
 *
 * HandshakeInterceptor WebSocket握手请求的拦截器.
 * 检查握手请求和响应, 对WebSocketHandler传递属性
 *
 * @author Created by Xu
 */
@Slf4j
public class XuHandshakeInterceptor extends TextWebSocketHandler implements HandshakeInterceptor {
    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
     * 通过attributes参数设置 WebSocketSession 的属性
     */
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) {
        log.info("开始握手, URI 是: [{}] ", request.getURI().toString());
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        // 从参数中获取 token
        String token = servletRequest.getParameter("token");
        if (StringUtils.isNotEmpty(token)) {
            // 解析token获取用户信息
            XuPrincipal user = CacheUtil.getUser(token);
            if (user == null) {
                log.debug(" token 已失效");
            } else {
                //保存认证用户
                attributes.put("user", user);
            }
        } else {
            log.debug("未从 parameter 中获取 token ");
        }
        return true;
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     */

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
        if (exception != null) {
            log.error("握手发生异常", exception);
        }
    }

    /**
     * 用户进入系统监听
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("xxx用户进入系统。。。");
        log.info("用户信息:" + session.getAttributes());
        System.out.println(session.getAttributes());
        System.out.println("xxx用户进入系统。。。");

        Map<String, Object> map = session.getAttributes();
        for (String key : map.keySet()) {
            System.out.println("key:" + key + " and value:" + map.get(key));
        }
    }


}
