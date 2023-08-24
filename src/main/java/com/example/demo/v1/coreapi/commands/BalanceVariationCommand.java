package com.example.demo.v1.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class BalanceVariationCommand {
  @TargetAggregateIdentifier private final String userId;
  private final Integer points;
  private final String variationStatus;

  public BalanceVariationCommand(String userId, Integer points, String variationStatus) {
    this.userId = userId;
    this.points = points;
    this.variationStatus = variationStatus;
  }

  public String getUserId() {
    return userId;
  }

  public Integer getPoints() {
    return points;
  }

  public String getVariationStatus() {
    return variationStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BalanceVariationCommand)) return false;
    BalanceVariationCommand that = (BalanceVariationCommand) o;
    return Objects.equals(getUserId(), that.getUserId())
        && Objects.equals(getPoints(), that.getPoints())
        && Objects.equals(getVariationStatus(), that.getVariationStatus());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getPoints(), getVariationStatus());
  }

  @Override
  public String toString() {
    return "BalanceVariationCommand{"
        + "userId='"
        + userId
        + '\''
        + ", points="
        + points
        + ", variationStatus='"
        + variationStatus
        + '\''
        + '}';
  }
}
