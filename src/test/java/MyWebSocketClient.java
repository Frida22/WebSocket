import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.net.URI;
import java.util.Arrays;
import java.util.Map;

public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public MyWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
//        send("Hello, it's me");
        System.out.println(Arrays.toString(handShakeData.getContent()));
    }

    @Override
    public void onMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();
        try {
            JSONObject messageLikeJson = (JSONObject) parser.parse(message);

            System.out.println("Get message :" + mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(messageLikeJson));
        } catch (ParseException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
        @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }
}
