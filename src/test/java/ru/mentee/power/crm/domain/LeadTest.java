package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Lead")
class LeadTest {

  @Test
  @DisplayName("getId() выводит значение поля id у объекта Lead")
  void shouldReturnIdWhenGetIdCalled() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead lead = new Lead(uuid, "test@example.com", "+71234567890", "TestCorp", "NEW");

    // Then
    assertThat(lead.id()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("getEmail() выводит значение поля email у объекта Lead")
  void shouldReturnEmailWhenGetEmailCalled() {
    // Given
    Lead lead = new Lead( UUID.randomUUID(),
         "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String email = lead.email();

    // Then
    assertThat(email).isEqualTo("test@example.com");
  }

  @Test
  @DisplayName("getPhone() выводит значение поля phone у объекта Lead")
  void shouldReturnPhoneWhenGetPhoneCalled() {
    // Given
    Lead lead = new Lead( UUID.randomUUID(),
        "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String phone = lead.phone();

    // Then
    assertThat(phone).isEqualTo("+71234567890");
  }

  @Test
  @DisplayName("getCompany() выводит значение поля company у объекта Lead")
  void shouldReturnCompanyWhenGetCompanyCalled() {
    // Given
    Lead lead = new Lead( UUID.randomUUID(),
         "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String company = lead.company();

    // Then
    assertThat(company).isEqualTo("TestCorp");
  }

  @Test
  @DisplayName("getStatus() выводит значение поля status у объекта Lead")
  void shouldReturnStatusWhenGetStatusCalled() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(),
         "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String status = lead.status();

    // Then
    assertThat(status).isEqualTo("NEW");
  }

  @Test
  @DisplayName("getStatus() выводит значение поля status у объекта Lead")
  void shouldReturnFormattedStringWhenToStringCalled() {
    // Given
    UUID uuid = UUID.randomUUID();

    Lead lead = new Lead(uuid,
         "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String toString = lead.toString();
    String exptedString = "Lead[id=" + String.valueOf(uuid)
        + ", email=test@example.com, phone=+71234567890, company=TestCorp, status=NEW]";

    // Then
    assertThat(toString).isEqualTo(exptedString);

  }

}