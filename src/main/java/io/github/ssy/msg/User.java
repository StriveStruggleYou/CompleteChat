package io.github.ssy.msg;

public class User {

  private String userId;


  private String userName;


  private String userAvatar;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserAvatar() {
    return userAvatar;
  }

  public void setUserAvatar(String userAvatar) {
    this.userAvatar = userAvatar;
  }

  @Override
  public String toString() {
    return "{" +
        "userId='" + userId + '\'' +
        ", userName='" + userName + '\'' +
        ", userAvatar='" + userAvatar + '\'' +
        '}';
  }
}
