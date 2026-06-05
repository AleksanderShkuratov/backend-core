package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Lead")
class LeadTest {

  @Test
  @DisplayName("getId() выводит значение поля id у объекта Lead")
  void shouldReturnIdWhenGetIdCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String id = lead.getId();

    // Then
    assertThat(id).isEqualTo("L1");
  }

  @Test
  @DisplayName("getEmail() выводит значение поля email у объекта Lead")
  void shouldReturnEmailWhenGetEmailCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String email = lead.getEmail();

    // Then
    assertThat(email).isEqualTo("test@example.com");
  }

  @Test
  @DisplayName("getPhone() выводит значение поля phone у объекта Lead")
  void shouldReturnPhoneWhenGetPhoneCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String phone = lead.getPhone();

    // Then
    assertThat(phone).isEqualTo("+71234567890");
  }

  @Test
  @DisplayName("getCompany() выводит значение поля company у объекта Lead")
  void shouldReturnCompanyWhenGetCompanyCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String company = lead.getCompany();

    // Then
    assertThat(company).isEqualTo("TestCorp");
  }

  @Test
  @DisplayName("getStatus() выводит значение поля status у объекта Lead")
  void shouldReturnStatusWhenGetStatusCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String status = lead.getStatus();

    // Then
    assertThat(status).isEqualTo("NEW");
  }

  @Test
  @DisplayName("getStatus() выводит значение поля status у объекта Lead")
  void shouldReturnFormattedStringWhenToStringCalled() {
    // Given
    Lead lead = new Lead(
        "L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String toString = lead.toString();

    // Then
    assertThat(toString).isEqualTo(
        "Lead{id='L1', email='test@example.com', phone='+71234567890', company='TestCorp', status='NEW'}");
  }


}