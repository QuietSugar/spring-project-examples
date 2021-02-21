package name.xu.webSocket.client;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * @author Created by Xu
 */
@Slf4j
public class CustmStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustmStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers,
                                byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }


    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("接收订阅消息=" + (String) payload);
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable exception) {
        log.error("handleTransportError error",exception);
        //super.handleTransportError(stompSession, exception);
        //try {
        //    Thread.sleep(3000);
        //    ReceiveTextStompClient.connect();
        //} catch (InterruptedException e) {
        //    log.error("",e);
        //}
    }

}
