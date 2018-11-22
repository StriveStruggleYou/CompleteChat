package io.github.ssy.connection;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.protocol.Packet;
import com.corundumstudio.socketio.protocol.PacketType;
import java.util.HashMap;
import java.util.Map;

public class ConnectListenerImpl implements ConnectListener {

  @Override
  public void onConnect(SocketIOClient socketIOClient) {

    Map map = new HashMap<String, String>();
    map.put("id", "T9e-SJ8iNISy");
    map.put("size", "67");
    Packet packet = new Packet(PacketType.UPGRADE);
    packet.setData(map);
    socketIOClient.sendEvent("connected",map);
  }
}
