package name.xu.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

/**
 * @author Created by Xu
 */
@Component
@Slf4j
public class PrincipalHandshakeHandler extends DefaultHandshakeHandler {


    /**
     * 可以根据http的session信息构造用户信息
     *
     * @param request
     * @param wsHandler
     * @param attributes
     * @return
     */
    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        log.info("=============================PrincipalHandshakeHandler.determineUser  ");

        log.info("determineUser: {}",  attributes.get("user"));

        // if (!StringUtils.isEmpty(token)) {
        //     return new XuPrincipal(token);
        // }
        return null;
    }

    private HttpSession getSession(ServerHttpRequest request) {
        ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
        return serverRequest.getServletRequest().getSession(true);
    }
}
