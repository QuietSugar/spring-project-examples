package name.xu.controller;

import lombok.extern.slf4j.Slf4j;
import name.xu.webSocket.client.XuWebSocketStompClient;
import org.junit.Test;

/**
 * @author Created by Xu
 */
@Slf4j
public class WbControllerTest {
    @Test
    public void test() {
        String url = "http://10.1.12.189:30001/websocket/server";
        XuWebSocketStompClient client = XuWebSocketStompClient.create(url);
        client.connect();
        client.send("/welcome", "Hello I'm client");
    }
}
