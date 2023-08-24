package com.example.demo.v1.coreapi.events;

import java.util.Objects;

public class IncreasedUserPointsEvent {
  private final String userId;
  private final Integer points;

  public IncreasedUserPointsEvent(String userId, Integer points) {
    this.userId = userId;
    this.points = points;
  }

  public String getUserId() {
    return userId;
  }

  public Integer getPoints() {
    return points;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IncreasedUserPointsEvent)) return false;
    IncreasedUserPointsEvent that = (IncreasedUserPointsEvent) o;
    return Objects.equals(getUserId(), that.getUserId())
        && Objects.equals(getPoints(), that.getPoints());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getPoints());
  }

  @Override
  public String toString() {
    return "IncreasedUserPoints{" + "userId='" + userId + '\'' + ", points=" + points + '}';
  }
}
