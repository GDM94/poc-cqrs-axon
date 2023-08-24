package com.example.demo.v1.commandmodel;

import com.example.demo.v1.coreapi.BalanceVariationStatus;
import com.example.demo.v1.coreapi.commands.BalanceVariationCommand;
import com.example.demo.v1.coreapi.commands.CreateUserCommand;
import com.example.demo.v1.coreapi.events.DecreasedUserPointsEvent;
import com.example.demo.v1.coreapi.events.IncreasedUserPointsEvent;
import com.example.demo.v1.coreapi.events.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
public class UserAggregate {
  @AggregateIdentifier private String userId;
  private Integer points;

  @CommandHandler
  public UserAggregate (CreateUserCommand createUserCommand) {
    apply(new UserCreatedEvent(createUserCommand.getUserId()));
  }

  @CommandHandler
  public void handle(BalanceVariationCommand balanceVariationCommand) {
    final var variationStatus = balanceVariationCommand.getVariationStatus();
    if (Objects.equals(variationStatus, BalanceVariationStatus.POSITIVE_VARIATION)) {
      apply(
          new IncreasedUserPointsEvent(
              balanceVariationCommand.getUserId(), balanceVariationCommand.getPoints()));
    } else if (Objects.equals(variationStatus, BalanceVariationStatus.NEGATIVE_VARIATION)) {
      apply(
          new DecreasedUserPointsEvent(
              balanceVariationCommand.getUserId(), balanceVariationCommand.getPoints()));
    }
  }

  @EventSourcingHandler
  public void on(UserCreatedEvent userCreatedEvent) {
    userId = userCreatedEvent.getUserId();
    points = 0;
  }

  @EventSourcingHandler
  public void on(IncreasedUserPointsEvent increasedUserPointsEvent) {
    userId = increasedUserPointsEvent.getUserId();
    points = points + increasedUserPointsEvent.getPoints();
  }

  @EventSourcingHandler
  public void on(DecreasedUserPointsEvent decreasedUserPointsEvent) {
    userId = decreasedUserPointsEvent.getUserId();
    points = points - decreasedUserPointsEvent.getPoints();
  }

  protected UserAggregate() {
    // Required by Axon to build a default Aggregate prior to Event Sourcing
  }
}
