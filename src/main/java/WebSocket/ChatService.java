package WebSocket;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by matt on 19.01.2016.
 */
public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService(){
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data){
        for (ChatWebSocket user : webSockets){
            user.sendString(data);
        }
    }

    public void add(ChatWebSocket webSocket){
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket){
        webSockets.remove(webSocket);
    }

}
