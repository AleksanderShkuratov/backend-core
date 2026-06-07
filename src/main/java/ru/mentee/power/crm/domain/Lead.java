package ru.mentee.power.crm.domain;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record Lead(
    UUID id,
    Contact contact,
    String company,
    String status
) {

  // Компактный конструктор для валидации
  public Lead {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    if (contact == null) {
      throw new IllegalArgumentException("Contact cannot be null");
    }
    if (status == null || status.isBlank()) {
      throw new IllegalArgumentException("Status cannot be null or blank");
    }

    Set<String> allowedStatuses = Set.of("NEW", "CONTACTED", "CLOSED");
    if (!allowedStatuses.contains(status)) {
      throw new IllegalArgumentException(
          "Status must be one of: 'NEW', 'CONTACTED', 'CLOSED'. Got: '" + status + "'"
      );
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lead lead = (Lead) o;
    return Objects.equals(id(), lead.id());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id());
  }
}
