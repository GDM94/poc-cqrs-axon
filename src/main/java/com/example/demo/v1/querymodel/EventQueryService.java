package com.example.demo.v1.querymodel;

import com.example.demo.v1.coreapi.queries.UserEventsQuery;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventQueryService {

  private final QueryGateway queryGateway;

  public EventQueryService(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  public List<DomainEventMessage> getUserEvents(String userId, Long events) {
    return queryGateway
        .query(
            new UserEventsQuery(userId, events),
            ResponseTypes.multipleInstancesOf(DomainEventMessage.class))
        .thenApply(r -> r.stream().collect(Collectors.toList()))
        .join();
  }
}
