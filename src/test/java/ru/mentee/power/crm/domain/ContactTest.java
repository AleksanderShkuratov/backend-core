package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactTest {

  private static final String CITY = "Klin";
  private static final String STREET = "Lenina";
  private static final String ZIP = "141601";
  private static final String EMAIL = "test@example.com";
  private static final String PHONE = "+71234567890";

  @Test
  void shouldCreateContactWhenValidData() {
    // Given
    Address address = new Address(CITY, STREET, ZIP);
    Contact contact = new Contact(EMAIL, PHONE, address);

    // When
    String email = contact.email();
    String phone = contact.phone();
    Address resultAddress = contact.address();
    String city = contact.address().city();
    String street = contact.address().street();
    String zip = contact.address().zip();

    // Then
    assertThat(email).isEqualTo(EMAIL);
    assertThat(phone).isEqualTo(PHONE);
    assertThat(resultAddress).isEqualTo(address);
    assertThat(city).isEqualTo(CITY);
    assertThat(street).isEqualTo(STREET);
    assertThat(zip).isEqualTo(ZIP);
  }

  @Test
  @DisplayName("Проверка что Contact делегирует адресные данные объекту Address")
  void shouldDelegateContactWhenValidData() {
    // Given
    Address address = new Address(CITY, STREET, ZIP);
    Contact contact = new Contact(EMAIL, PHONE, address);

    // When
    String city = contact.address().city();
    String street = contact.address().street();
    String zip = contact.address().zip();

    // Then
    assertThat(city).isEqualTo(CITY);
    assertThat(street).isEqualTo(STREET);
    assertThat(zip).isEqualTo(ZIP);
  }

  @Test
  void shouldBeEqualWhenSameData() {
    // Given
    Address address = new Address(CITY, STREET, ZIP);
    Contact contact1 = new Contact(EMAIL, PHONE, address);
    Contact contact2 = new Contact(EMAIL, PHONE, address);

    // Then
    assertThat(contact1).isEqualTo(contact2);
    assertThat(contact1.hashCode()).isEqualTo(contact2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentData() {
    // Given
    Address address = new Address(CITY, STREET, ZIP);
    Contact contact1 = new Contact(EMAIL, PHONE, address);
    Contact contact2 = new Contact("sale@yandex.ru", PHONE, address);

    // Then
    assertThat(contact1).isNotEqualTo(contact2);
    assertThat(contact1.hashCode()).isNotEqualTo(contact2.hashCode());
  }

  @Test
  @DisplayName("Создание Contact с email=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenEmailIsNull() {
    Address address = new Address(CITY, STREET, ZIP);
    assertThatThrownBy(() -> new Contact(null, PHONE, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с email=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenEmaiIsBlank() {
    Address address = new Address(CITY, STREET, ZIP);
    assertThatThrownBy(() -> new Contact("", PHONE, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с phone=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenPhoneIsNull() {
    Address address = new Address(CITY, STREET, ZIP);
    assertThatThrownBy(() -> new Contact(EMAIL, null, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с phone=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenPhoneIsBlank() {
    Address address = new Address(CITY, STREET, ZIP);
    assertThatThrownBy(() -> new Contact(EMAIL, "", address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с address=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenAddressIsNull() {
    assertThatThrownBy(() -> new Contact(EMAIL, PHONE, null))
        .isInstanceOf(IllegalArgumentException.class);
  }

}