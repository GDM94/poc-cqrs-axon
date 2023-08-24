package com.example.demo.v1.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class CreateUserCommand {

  @TargetAggregateIdentifier private final String userId;

  public CreateUserCommand(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CreateUserCommand)) return false;
    CreateUserCommand that = (CreateUserCommand) o;
    return Objects.equals(getUserId(), that.getUserId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId());
  }

  @Override
  public String toString() {
    return "CreateUserCommand{" + "userId='" + userId + '\'' + '}';
  }
}
