package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Address")
class AddressTest {

  private static final String CITY = "Klin";
  private static final String STREET = "Lenina";
  private static final String ZIP = "141601";

  @Test
  @DisplayName("Проверка что все геттеры возращают правильные значения")
  void shouldCreateAddressWhenValidData() {
    // Given
    Address address = new Address(CITY, STREET, ZIP);

    // When
    String city = address.city();
    String street = address.street();
    String zip = address.zip();

    // Then
    assertThat(city).isEqualTo(CITY);
    assertThat(street).isEqualTo(STREET);
    assertThat(zip).isEqualTo(ZIP);
  }

  @Test
  @DisplayName("Проверка одинаковых Address - одинаковый хеш-код и они равны через equals")
  void shouldBeEqualWhenSameData() {
    // Given
    Address address1 = new Address(CITY, STREET, ZIP);
    Address address2 = new Address(CITY, STREET, ZIP);

    // Then
    assertThat(address1).isEqualTo(address2);
    assertThat(address1.hashCode()).isEqualTo(address2.hashCode());
  }

  @Test
  @DisplayName("Создание Address с city=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenCityIsNull() {
    assertThatThrownBy(() -> new Address(null, STREET, ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с city=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenCityIsBlank() {
    assertThatThrownBy(() -> new Address("", STREET, ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с street=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStreetIsNull() {
    assertThatThrownBy(() -> new Address(CITY, null, ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с street=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStreetIsBlank() {
    assertThatThrownBy(() -> new Address(CITY, "", ZIP))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с zip=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenZipIsNull() {
    assertThatThrownBy(() -> new Address(CITY, STREET, null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Address с zip=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenZipIsBlank() {
    assertThatThrownBy(() -> new Address(CITY, STREET, ""))
        .isInstanceOf(IllegalArgumentException.class);
  }

}