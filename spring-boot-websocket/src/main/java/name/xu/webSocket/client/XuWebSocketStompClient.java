package name.xu.webSocket.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * WebSocket
 * Stomp
 * 客户端
 *
 * @author Created by Xu
 */
@Slf4j
public class XuWebSocketStompClient {

    private String url;
    /**
     * 定义全局变量，代表一个session
     */
    private StompSession stompSession;

    public XuWebSocketStompClient(String url) {
        this.url = url;
    }

    public static XuWebSocketStompClient create(String url) {
        return new XuWebSocketStompClient(url);
    }

    /**
     * 定义连接函数
     */
    public void connect() {
        if (stompSession == null || !stompSession.isConnected()) {
            log.info("当前处于断开状态,尝试连接");
            List<Transport> transports = new ArrayList<>();
            transports.add(new WebSocketTransport(new StandardWebSocketClient()));
            SockJsClient sockJsClient = new SockJsClient(transports);
            WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);
            webSocketStompClient.setMessageConverter(new StringMessageConverter());
            // 心跳
            webSocketStompClient.setDefaultHeartbeat(new long[]{20000, 0});
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
            taskScheduler.afterPropertiesSet();
            webSocketStompClient.setTaskScheduler(taskScheduler);
            WebSocketHttpHeaders webSocketHttpHeaders = null;
            // head 可以放 token
            StompHeaders stompHeaders = new StompHeaders();
            StompSessionHandler receiveTextStompSessionHandler = new CustmStompSessionHandler();
            try {
                ListenableFuture<StompSession> future = webSocketStompClient.connect(
                        url,
                        webSocketHttpHeaders,
                        stompHeaders,
                        receiveTextStompSessionHandler
                );
                stompSession = future.get();
                stompSession.setAutoReceipt(true);
            } catch (Exception e) {
                log.error("connect error", e);
            }
        } else {
            log.info("connected");
        }
    }

    /**
     * 发送消息
     */
    public StompSession.Receiptable send(String destination, Object payload) {
        return stompSession.send(destination, payload);
    }

    /**
     * 订阅
     */
    public StompSession.Subscription subscribe(String destination, StompSessionHandler stompSessionHandler) {
        return stompSession.subscribe(destination, stompSessionHandler);
    }
}
