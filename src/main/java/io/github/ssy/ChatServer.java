package io.github.ssy;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import io.github.ssy.connection.ConnectListenerImpl;
import io.github.ssy.connection.DisconnectListenerImpl;
import io.github.ssy.msg.Broadcast;
import io.github.ssy.msg.ChatObject;
import io.github.ssy.msg.Gm;
import io.github.ssy.msg.User;
import io.github.ssy.timer.PongTimer;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class ChatServer {

  public static void main(String[] args) throws Exception {

    Properties properties = new Properties();
    InputStream in = ChatServer.class.getClassLoader().getResourceAsStream("conf.properties");
    properties.load(in);
    //获取key对应的value值
    String hostName = properties.getProperty("hostname");

    String port = properties.getProperty("port");

    Configuration config = new Configuration();
    config.setHostname(hostName);
    config.setPort(Integer.valueOf(port));

    final Map<String, User> userMap = new HashMap<String, User>();

    final SocketIOServer server = new SocketIOServer(config);

    //设置建立连接过程数据信息
    server.addConnectListener(new ConnectListenerImpl());

    server.addEventListener("gm", Gm.class, new DataListener<Gm>() {
      @Override
      public void onData(SocketIOClient client, Gm data, AckRequest ackRequest) {
        // broadcast messages to all clients
        System.out.println("data:" + data.toString());
        Broadcast broadcast = new Broadcast();
        broadcast.setMsg(data.getMsg());
        broadcast.setId(client.getSessionId().toString());

        User user = userMap.get(client.getSessionId().toString());
        if (user != null) {
          broadcast.setName(user.getUserName());
          broadcast.setAvatar("https://static.oschina.net/uploads/user/1142/2285811_200.jpg");
          broadcast.setType("BROADCAST");
        } else {
          broadcast.setName(UUID.randomUUID().toString());
          broadcast.setAvatar("https://static.oschina.net/uploads/user/1142/2285811_200.jpg");
          broadcast.setType("BROADCAST");
        }
        //过滤掉自己的内容
        Collection<SocketIOClient> clients = server.getBroadcastOperations().getClients();
        for (SocketIOClient socketIOClient : clients) {
          if (socketIOClient.getSessionId() != client.getSessionId()) {
            socketIOClient.sendEvent("broadcast", broadcast);
          }
        }
      }
    });

    //创建用户,关联起来用户信息
    server.addEventListener("createUser", User.class, new DataListener<User>() {
      @Override
      public void onData(SocketIOClient client, User data, AckRequest ackRequest) {
        // broadcast messages to all clients
        System.out.println("data:" + data.toString());
        Broadcast broadcast = new Broadcast();
        broadcast.setMsg("欢迎进入我的联盟");
        broadcast.setId(data.getUserId());
        broadcast.setName(data.getUserName());
        broadcast.setAvatar("https://static.oschina.net/uploads/user/1142/2285811_200.jpg");
        broadcast.setType("NEW");
        //设置基础数据信息
        data.setUserAvatar("https://static.oschina.net/uploads/user/1142/2285811_200.jpg");
        userMap.put(client.getSessionId().toString(), data);
        server.getBroadcastOperations().sendEvent("broadcast", broadcast);
      }
    });

    //设置断开连接的操作
    server.addDisconnectListener(new DisconnectListenerImpl(server, userMap));

    Thread time = new Thread(new PongTimer(server, userMap));

    time.start();

    //42["broadcast",{"name":"SYSTEM","msg":"用户 eWfYF67QqI 离开群聊","type":"LEAVE"}]
    Thread serverThread = new Thread(new Runnable() {
      @Override
      public void run() {
        server.start();
      }
    });

    serverThread.start();

  }


}
