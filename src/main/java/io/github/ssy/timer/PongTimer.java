package io.github.ssy.timer;

import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOServer;
import java.util.HashMap;
import java.util.List;
import io.github.ssy.msg.User;
import java.util.ArrayList;
import java.util.Map;

public class PongTimer implements Runnable {

  SocketIOServer server;

  Map<String, User> userMap;

  public PongTimer(SocketIOServer server, Map userMap) {

    this.userMap = userMap;
    this.server = server;
  }

  @Override
  public void run() {
    while (true) {
      try {
        BroadcastOperations broadcastOperations = server.getBroadcastOperations();
        int size = broadcastOperations.getClients().size();
        List<User> users = new ArrayList<User>();
        for (String str : userMap.keySet()) {
          if (userMap.get(str) != null) {
            users.add(userMap.get(str));
          }
        }
        Map result = new HashMap();
        result.put("count", size);
        result.put("users", users);
        result.put("type", "PONG");
        server.getBroadcastOperations().sendEvent("pong", result);
        Thread.sleep(3000);
      } catch (Exception e) {
        System.out.println(e.getCause());
      }

    }

  }
}
