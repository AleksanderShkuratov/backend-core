package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ContactTest {

  @Test
  void shouldCreateContactWhenValidData() {
    // Given
    Contact contact = new Contact("Petr", "Famusov", "test@example.com");

    // When
    String firstName = contact.getFirstName();
    String lastName = contact.getLastName();
    String email = contact.getEmail();

    // Then
    assertThat(firstName).isEqualTo("Petr");
    assertThat(lastName).isEqualTo("Famusov");
    assertThat(email).isEqualTo("test@example.com");
  }

  @Test
  void shouldBeEqualWhenSameData() {
    // Given
    Contact contact1 = new Contact("Petr", "Famusov", "test@example.com");
    Contact contact2 = new Contact("Petr", "Famusov", "test@example.com");

    // Then
    assertThat(contact1).isEqualTo(contact2);
    assertThat(contact1.hashCode()).isEqualTo(contact2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentData() {
    // Given
    Contact contact1 = new Contact("Petr", "Famusov", "test@example.com");
    Contact contact2 = new Contact("Pavel", "Famusov", "test@example.com");

    // Then
    assertThat(contact1).isNotEqualTo(contact2);
    assertThat(contact1.hashCode()).isNotEqualTo(contact2.hashCode());
  }
}