package ru.mentee.power.crm.model;

import java.util.Objects;

public record Lead(
    String id,
    String email,
    String phone,
    String company,
    String status
) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ru.mentee.power.crm.domain.Lead lead = (ru.mentee.power.crm.domain.Lead) o;
    return Objects.equals(id(), lead.id());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id());
  }
}
