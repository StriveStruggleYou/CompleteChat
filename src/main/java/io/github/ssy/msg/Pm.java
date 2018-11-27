package io.github.ssy.msg;

public class Pm {

  private String msg;

  private String targetId;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getTargetId() {
    return targetId;
  }

  public void setTargetId(String targetId) {
    this.targetId = targetId;
  }

  @Override
  public String toString() {
    return "Gm{" +
        "msg='" + msg + '\'' +
        ", targetId='" + targetId + '\'' +
        '}';
  }

}
