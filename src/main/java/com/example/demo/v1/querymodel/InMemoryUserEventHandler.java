package com.example.demo.v1.querymodel;

import com.example.demo.v1.coreapi.events.DecreasedUserPointsEvent;
import com.example.demo.v1.coreapi.events.IncreasedUserPointsEvent;
import com.example.demo.v1.coreapi.events.UserCreatedEvent;
import com.example.demo.v1.coreapi.queries.User;
import com.example.demo.v1.coreapi.queries.UserEventsQuery;
import com.example.demo.v1.coreapi.queries.UserPointsQuery;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.MessageIdentifier;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("users")
public class InMemoryUserEventHandler {

  Map<String, User> users = new HashMap<>();

  private final EventStore eventStore;

  public InMemoryUserEventHandler(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  @EventHandler
  public void on(UserCreatedEvent event) {
    final var userId = event.getUserId();
    users.put(userId, new User(userId));
  }

  @EventHandler
  public void on(IncreasedUserPointsEvent event, @MessageIdentifier String eventId) {
    users.computeIfPresent(
        event.getUserId(),
        (userId, user) -> {
          user.addPoints(event.getPoints());
          return user;
        });
  }

  @EventHandler
  public void on(DecreasedUserPointsEvent event) {
    users.computeIfPresent(
        event.getUserId(),
        (userId, user) -> {
          user.removePoints(event.getPoints());
          return user;
        });
  }

  @QueryHandler
  public User handle(UserPointsQuery query) {
    return users.get(query.getUserId());
  }

  @QueryHandler
  public List<DomainEventMessage> handle(UserEventsQuery query) {
    return eventStore
        .readEvents(query.getUserId(), query.getEvents())
        .asStream()
        .collect(Collectors.toList());
  }
}
