package ru.mentee.power.crm.domain;

import java.util.Set;
import java.util.UUID;

public record Customer(
    UUID id,
    Contact contact,
    Address billingAddress,
    String loyaltyTier
) {

  // Компактный конструктор для валидации
  public Customer {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    if (contact == null) {
      throw new IllegalArgumentException("Contact cannot be null");
    }
    if (billingAddress == null) {
      throw new IllegalArgumentException("BillingAddress cannot be null");
    }
    if (loyaltyTier == null || loyaltyTier.isBlank()) {
      throw new IllegalArgumentException("Status cannot be null or blank");
    }

    Set<String> allowedLoyaltyTier = Set.of("BRONZE", "SILVER", "GOLD");
    if (!allowedLoyaltyTier.contains(loyaltyTier)) {
      throw new IllegalArgumentException(
          "LoyaltyTier must be one of: 'BRONZE', 'SILVER', 'GOLD'. Got: '" + loyaltyTier + "'"
      );
    }
  }

}
