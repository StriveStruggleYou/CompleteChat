package io.github.ssy.msg;

public class Broadcast {

  private String msg;

  private String id;

  private String name;

  private String avatar;

  private String type;


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "{" +
        "msg='" + msg + '\'' +
        ", id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", avatar='" + avatar + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
