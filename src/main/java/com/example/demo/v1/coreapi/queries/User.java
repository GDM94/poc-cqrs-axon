package com.example.demo.v1.coreapi.queries;

import java.util.Objects;

public class User {

  private final String userId;
  private Integer points;

  public User(String userId) {
    this.userId = userId;
    this.points = 0;
  }

  public String getUserId() {
    return userId;
  }

  public Integer getPoints() {
    return points;
  }

  public void addPoints(Integer points){
    this.points += points;
  }

  public void removePoints(Integer points){
    this.points -= points;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(getUserId(), user.getUserId())
        && Objects.equals(getPoints(), user.getPoints());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getPoints());
  }

  @Override
  public String toString() {
    return "User{" + "userId='" + userId + '\'' + ", points=" + points + '}';
  }
}
