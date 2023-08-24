package com.example.demo.v1.coreapi.events;

import java.util.Objects;

public class UserCreatedEvent {

  private final String userId;

  public UserCreatedEvent(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserCreatedEvent)) return false;
    UserCreatedEvent that = (UserCreatedEvent) o;
    return Objects.equals(getUserId(), that.getUserId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId());
  }

  @Override
  public String toString() {
    return "UserCreatedEvent{" + "userId='" + userId + '\'' + '}';
  }
}
