package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_CITY;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_EMAIL;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_PHONE;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_STREET;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_ZIP;
import static ru.mentee.power.crm.util.TestObjectFactory.createAddress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Contact")
class ContactTest {

  @Test
  void shouldCreateContactWhenValidData() {
    // Given
    Address address = createAddress();
    Contact contact = new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, address);

    // When
    String email = contact.email();
    String phone = contact.phone();
    Address resultAddress = contact.address();
    String city = contact.address().city();
    String street = contact.address().street();
    String zip = contact.address().zip();

    // Then
    assertThat(email).isEqualTo(DEFAULT_EMAIL);
    assertThat(phone).isEqualTo(DEFAULT_PHONE);
    assertThat(resultAddress).isEqualTo(address);
    assertThat(city).isEqualTo(DEFAULT_CITY);
    assertThat(street).isEqualTo(DEFAULT_STREET);
    assertThat(zip).isEqualTo(DEFAULT_ZIP);
  }

  @Test
  @DisplayName("Проверка что Contact делегирует адресные данные объекту Address")
  void shouldDelegateContactWhenValidData() {
    // Given
    Address address = createAddress();
    Contact contact = new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, address);

    // When
    String city = contact.address().city();
    String street = contact.address().street();
    String zip = contact.address().zip();

    // Then
    assertThat(city).isEqualTo(DEFAULT_CITY);
    assertThat(street).isEqualTo(DEFAULT_STREET);
    assertThat(zip).isEqualTo(DEFAULT_ZIP);
  }

  @Test
  void shouldBeEqualWhenSameData() {
    // Given
    Address address = createAddress();
    Contact contact1 = new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, address);
    Contact contact2 = new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, address);

    // Then
    assertThat(contact1).isEqualTo(contact2);
    assertEquals(contact1.hashCode(), contact2.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentData() {
    // Given
    Address address = createAddress();
    Contact contact1 = new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, address);
    Contact contact2 = new Contact("sale@yandex.ru", DEFAULT_PHONE, address);

    // Then
    assertThat(contact1).isNotEqualTo(contact2);
    assertThat(contact1.hashCode()).isNotEqualTo(contact2.hashCode());
  }

  @Test
  @DisplayName("Создание Contact с email=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenEmailIsNull() {
    Address address = createAddress();
    assertThatThrownBy(() -> new Contact(null, DEFAULT_PHONE, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с email=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenEmaiIsBlank() {
    Address address = createAddress();
    assertThatThrownBy(() -> new Contact("", DEFAULT_PHONE, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с phone=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenPhoneIsNull() {
    Address address = createAddress();
    assertThatThrownBy(() -> new Contact(DEFAULT_EMAIL, null, address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с phone=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenPhoneIsBlank() {
    Address address = createAddress();
    assertThatThrownBy(() -> new Contact(DEFAULT_EMAIL, "", address))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Contact с address=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenAddressIsNull() {
    assertThatThrownBy(() -> new Contact(DEFAULT_EMAIL, DEFAULT_PHONE, null))
        .isInstanceOf(IllegalArgumentException.class);
  }

}