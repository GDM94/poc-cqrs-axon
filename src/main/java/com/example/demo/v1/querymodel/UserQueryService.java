package com.example.demo.v1.querymodel;

import com.example.demo.v1.coreapi.queries.User;
import com.example.demo.v1.coreapi.queries.UserPointsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {

  private final QueryGateway queryGateway;

  public UserQueryService(QueryGateway queryGateway) {
    this.queryGateway = queryGateway;
  }

  public User getUser(String userId) {
    return queryGateway
        .query(new UserPointsQuery(userId), ResponseTypes.instanceOf(User.class))
        .join();
  }
}
