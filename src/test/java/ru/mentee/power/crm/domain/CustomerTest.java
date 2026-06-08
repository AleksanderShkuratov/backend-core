package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.mentee.power.crm.util.TestObjectFactory.createContact;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Customer")
class CustomerTest {

  @Test
  @DisplayName("Демонстрация: один Customer, два Address через композицию")
  void shouldReuseContactWhenCreatingCustomer() {
    // Given
    UUID uuid = UUID.randomUUID();
    Address billingAddress = new Address("Moscow", "Tverskaya", "125009");
    Customer customer = new Customer(uuid, createContact(), billingAddress, "SILVER");

    // Then
    assertThat(customer.contact().address()).isNotEqualTo(customer.billingAddress());
  }

  @Test
  @DisplayName("Демонстрация: Contact переиспользуется без дублирования кода")
  void shouldDemonstrateContactReuseAcrossLeadAndCustomer() {
    // Given
    UUID uuid = UUID.randomUUID();
    Address billingAddress = new Address("Moscow", "Tverskaya", "125009");

    Lead lead = new Lead(uuid, createContact(), "TestCorp", "NEW");
    Customer customer = new Customer(uuid, createContact(), billingAddress, "SILVER");

    // Then
    assertThat(lead.contact()).isEqualTo(customer.contact());
  }

}