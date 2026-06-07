package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Lead")
class LeadTest {

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
  @DisplayName("Проверка что lead.contact() возвращает правильный contact")
  void shouldCreateLeadWhenValidData() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead lead = new Lead(uuid, contact, "TestCorp", "NEW");

    // Then
    assertThat(lead.contact()).isEqualTo(contact);
  }

  @Test
  @DisplayName("Проверка что lead возвращает правильный email и city")
  void shouldAccessEmailThroughDelegationWhenLeadCreated() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String email = lead.contact().email();
    String city = lead.contact().address().city();

    // Then
    assertThat(email).isEqualTo(EMAIL);
    assertThat(city).isEqualTo(CITY);
  }

  @Test
  @DisplayName("Проверка отработки equals при одинаковом id, но других контактах")
  void shouldBeEqualWhenSameIdButDifferentContact() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead lead = new Lead(uuid, contact, "TechCorp", "NEW");
    Contact otherContact = new Contact("other@mail.com", PHONE, address);
    Lead leadDifferentContact = new Lead(uuid, otherContact, "TechCorp", "NEW");

    // Then
    assertThat(lead).isEqualTo(leadDifferentContact);
  }

  @Test
  @DisplayName("Создание Lead с id=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenIdIsNull() {
    assertThatThrownBy(() -> new Lead(null, contact, "TechCorp", "NEW")).isInstanceOf(
        IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Lead с Contact=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenContactIsNull() {
    UUID uuid = UUID.randomUUID();
    assertThatThrownBy(() -> new Lead(uuid, null, "TechCorp", "NEW")).isInstanceOf(
        IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Lead с status=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStatusIsNull() {
    UUID uuid = UUID.randomUUID();
    assertThatThrownBy(() -> new Lead(uuid, contact, "TechCorp", null)).isInstanceOf(
        IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Lead с status=  бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStatusIsBlank() {
    UUID uuid = UUID.randomUUID();
    assertThatThrownBy(() -> new Lead(uuid, contact, "TechCorp", "")).isInstanceOf(
        IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Создание Lead с не разрешенным status бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenStatusNoAllowedStatuses() {
    UUID uuid = UUID.randomUUID();
    assertThatThrownBy(() -> new Lead(uuid, contact, "TechCorp", "INWORK")).isInstanceOf(
        IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Демонстрация трехуровневой делегации на примере city")
  void shouldDemonstrateThreeLevelCompositionWhenAccessingCity() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String city = lead.contact().address().city();

    // Then
    assertThat(city).isEqualTo(CITY);
  }

  @Test
  @DisplayName("Id() выводит значение поля id у объекта Lead")
  void shouldReturnIdWhenGetIdCalled() {
    // Given
    UUID uuid = UUID.randomUUID();

    Lead lead = new Lead(uuid, contact, "TestCorp", "NEW");

    // Then
    assertThat(lead.id()).isEqualTo(uuid);
  }

  @Test
  @DisplayName("email() выводит значение поля email через делегацию")
  void shouldReturnEmailWhenGetEmailCalled() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String email = lead.contact().email();

    // Then
    assertThat(email).isEqualTo(EMAIL);
  }

  @Test
  @DisplayName("phone() выводит значение поля phone через делегацию")
  void shouldReturnPhoneWhenGetPhoneCalled() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String phone = lead.contact().phone();

    // Then
    assertThat(phone).isEqualTo(PHONE);
  }

  @Test
  @DisplayName("company() выводит значение поля company у объекта Lead")
  void shouldReturnCompanyWhenGetCompanyCalled() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String company = lead.company();

    // Then
    assertThat(company).isEqualTo("TestCorp");
  }

  @Test
  @DisplayName("status() выводит значение поля status у объекта Lead")
  void shouldReturnStatusWhenGetStatusCalled() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TestCorp", "NEW");

    // When
    String status = lead.status();

    // Then
    assertThat(status).isEqualTo("NEW");
  }

  @Test
  @DisplayName("status() выводит значение поля status у объекта Lead")
  void shouldReturnFormattedStringWhenToStringCalled() {
    // Given
    UUID uuid = UUID.randomUUID();

    Lead lead = new Lead(uuid, contact, "TestCorp", "NEW");

    // When
    String toString = lead.toString();
    String exptedString = "Lead[id=" + String.valueOf(uuid)
        + ", contact=Contact[email=test@example.com, phone=+71234567890,"
        + " address=Address[city=Klin, street=Lenina, zip=141601]]," + " company=TestCorp,"
        + " status=NEW]";

    // Then
    assertThat(toString).isEqualTo(exptedString);

  }

  @Test
  @DisplayName("демонстрация типобезопасности поля id у Lead")
  void shoulPreventStringConfusionWhenUsingUUID() {
    // Given
    UUID uuid = UUID.randomUUID();

    Lead lead = new Lead(uuid, contact, "TestCorp", "NEW");

    // When

    // findById("not-a-uuid") - не скомпилируется
    // Lead result = findById("123"); // ERROR: incompatible types

    Lead result = findById(uuid);

    // Then
    assertThat(result).isEqualTo(lead);

  }

  private Lead findById(UUID id) {
    // Заглушка
    return new Lead(id, contact, "TestCorp", "NEW");
  }

}