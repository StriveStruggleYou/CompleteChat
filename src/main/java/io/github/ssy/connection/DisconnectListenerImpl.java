package io.github.ssy.connection;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
import io.github.ssy.msg.Broadcast;
import java.util.Map;

public class DisconnectListenerImpl implements DisconnectListener {


  SocketIOServer server;

  Map userMap;


  public DisconnectListenerImpl(SocketIOServer server, Map userMap) {
    this.userMap = userMap;
    this.server = server;
  }

  public void onDisconnect(SocketIOClient socketIOClient) {
    System.out.println(socketIOClient.getSessionId());
    userMap.remove(socketIOClient.getSessionId());

    Broadcast broadcast = new Broadcast();

    broadcast.setName("SYSTEM");

    broadcast.setMsg("有用户离开群聊");

    broadcast.setAvatar("https://static.oschina.net/uploads/user/1142/2285811_200.jpg");

    broadcast.setType("LEAVE");
    server.getBroadcastOperations().sendEvent("broadcast",
        broadcast);
  }
}
