package com.example.demo.v1.coreapi.queries;

import java.util.Objects;

public class UserEventsQuery {
  private String userId;
  private Long events;

  public UserEventsQuery(String userId, Long events) {
    this.userId = userId;
    this.events = events;
  }

  public String getUserId() {
    return userId;
  }

  public Long getEvents() {
    return events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserEventsQuery)) return false;
    UserEventsQuery that = (UserEventsQuery) o;
    return Objects.equals(getUserId(), that.getUserId())
        && Objects.equals(getEvents(), that.getEvents());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getEvents());
  }

  @Override
  public String toString() {
    return "UserEventsQuery{" + "userId='" + userId + '\'' + ", events=" + events + '}';
  }
}
