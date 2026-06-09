package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_CITY;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_STREET;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_ZIP;
import static ru.mentee.power.crm.util.TestObjectFactory.createAddress;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Address")
class AddressTest {

  @Test
  @DisplayName("Проверка что все геттеры возращают правильные значения")
  void shouldCreateAddressWhenValidData() {
    // Given
    Address address = createAddress();

    // When
    String city = address.city();
    String street = address.street();
    String zip = address.zip();

    // Then
    assertThat(city).isEqualTo(DEFAULT_CITY);
    assertThat(street).isEqualTo(DEFAULT_STREET);
    assertThat(zip).isEqualTo(DEFAULT_ZIP);
  }

  @Test
  @DisplayName("Проверка одинаковых Address - одинаковый хеш-код и они равны через equals")
  void shouldBeEqualWhenSameData() {
    // Given
    Address address1 = createAddress();
    Address address2 = createAddress();

    // Then
    assertThat(address1).isEqualTo(address2);
    assertEquals(address1.hashCode(), address2.hashCode());
  }

  @Test
  @DisplayName("Создание Address с city=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenCityIsNull() {
    assertThatThrownBy(() -> new Address(null, DEFAULT_STREET, DEFAULT_ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с city=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenCityIsBlank() {
    assertThatThrownBy(() -> new Address("", DEFAULT_STREET, DEFAULT_ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с street=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStreetIsNull() {
    assertThatThrownBy(() -> new Address(DEFAULT_CITY, null, DEFAULT_ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с street=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStreetIsBlank() {
    assertThatThrownBy(() -> new Address(DEFAULT_CITY, "", DEFAULT_ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с zip=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenZipIsNull() {
    assertThatThrownBy(() -> new Address(DEFAULT_CITY, DEFAULT_STREET, null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с zip=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenZipIsBlank() {
    assertThatThrownBy(() -> new Address(DEFAULT_CITY, DEFAULT_STREET, ""))
        .isInstanceOf(IllegalArgumentException.class);
  }

}