package io.github.ssy.connection;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
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
    server.getBroadcastOperations().sendEvent("broadcast",
        "{\"name\":\"SYSTEM\",\"msg\":\"用户 eWfYF67QqI 离开群聊\",\"type\":\"LEAVE\"}");
  }
}
