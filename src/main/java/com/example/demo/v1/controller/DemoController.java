package com.example.demo.v1.controller;

import com.example.demo.v1.coreapi.BalanceVariationStatus;
import com.example.demo.v1.coreapi.commands.BalanceVariationCommand;
import com.example.demo.v1.coreapi.commands.CreateUserCommand;
import com.example.demo.v1.coreapi.queries.User;
import com.example.demo.v1.querymodel.EventQueryService;
import com.example.demo.v1.querymodel.UserQueryService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController("DemoController_v1")
@RequestMapping("/demo")
public class DemoController {

  private final CommandGateway commandGateway;
  private final UserQueryService userQueryService;
  private final EventQueryService eventQueryService;

  public DemoController(
      CommandGateway commandGateway,
      UserQueryService userQueryService,
      EventQueryService eventQueryService) {
    this.commandGateway = commandGateway;
    this.userQueryService = userQueryService;
    this.eventQueryService = eventQueryService;
  }

  @PostMapping("/user/{userId}")
  public CompletableFuture<String> createUser(@PathVariable("userId") String userId) {
    return commandGateway.send(new CreateUserCommand(userId));
  }

  @PostMapping("/user/{userId}/points/add/{points}")
  public CompletableFuture<Void> addUserPoints(
      @PathVariable("userId") String userId, @PathVariable("points") Integer points) {
    return commandGateway.send(
        new BalanceVariationCommand(userId, points, BalanceVariationStatus.POSITIVE_VARIATION));
  }

  @PostMapping("/user/{userId}/points/remove/{points}")
  public CompletableFuture<Void> removeUserPoints(
      @PathVariable("userId") String userId, @PathVariable("points") Integer points) {
    return commandGateway.send(
        new BalanceVariationCommand(userId, points, BalanceVariationStatus.NEGATIVE_VARIATION));
  }

  @GetMapping("/user/{userId}")
  public User getUser(@PathVariable("userId") String userId) {
    return userQueryService.getUser(userId);
  }

  @GetMapping("/user/{userId}/events/{eventsNumber}")
  public List<DomainEventMessage> getUserEvents(
      @PathVariable("userId") String userId, @PathVariable("eventsNumber") Long eventsNumber) {
    return eventQueryService.getUserEvents(userId, eventsNumber);
  }
}
