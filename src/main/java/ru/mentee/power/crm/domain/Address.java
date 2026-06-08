package ru.mentee.power.crm.domain;

public record Address(
    String city,
    String street,
    String zip
) {

  // Компактный конструктор для валидации
  public Address {
    if (city == null || city.isBlank()) {
      throw new IllegalArgumentException("City cannot be null or blank");
    }
    if (street == null || street.isBlank()) {
      throw new IllegalArgumentException("Street cannot be null or blank");
    }
    if (zip == null || zip.isBlank()) {
      throw new IllegalArgumentException("ZIP cannot be null or blank");
    }
  }
}
