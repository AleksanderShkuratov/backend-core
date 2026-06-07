package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Customer")
class CustomerTest {

  private static final String CITY = "Klin";
  private static final String STREET = "Lenina";
  private static final String ZIP = "141601";
  private static final String EMAIL = "test@example.com";
  private static final String PHONE = "+71234567890";
  private static Address address;
  private static Contact contact;

  @BeforeAll
  static void setUp() {
    address = new Address(CITY, STREET, ZIP);
    contact = new Contact(EMAIL, PHONE, address);
  }

  @Test
  @DisplayName("Демонстрация: один Customer, два Address через композицию")
  void shouldReuseContactWhenCreatingCustomer() {
    // Given
    UUID uuid = UUID.randomUUID();
    Address billingAddress = new Address("Moscow", "Tverskaya", "125009");
    Customer customer = new Customer(uuid, contact, billingAddress, "SILVER");

    // Then
    assertThat(customer.contact().address()).isNotEqualTo(customer.billingAddress());
  }

  @Test
  @DisplayName("Демонстрация: Contact переиспользуется без дублирования кода")
  void shouldDemonstrateContactReuseAcrossLeadAndCustomer() {
    // Given
    UUID uuid = UUID.randomUUID();
    Address billingAddress = new Address("Moscow", "Tverskaya", "125009");

    Lead lead = new Lead(uuid, contact, "TestCorp", "NEW");
    Customer customer = new Customer(uuid, contact, billingAddress, "SILVER");

    // Then
    assertThat(lead.contact()).isEqualTo(customer.contact());
  }

}