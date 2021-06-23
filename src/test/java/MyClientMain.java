import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class MyClientMain {


    public static void main(String[] args) {
        String url = System.getenv("SOCKET_LOCATION");
        String msg = "some message";
        try {
            WebSocketClient client = new MyWebSocketClient(new URI(url));
            try {
                client.connectBlocking();
                client.send(msg);
                Thread.sleep(3000);
                client.closeBlocking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
