package ru.mentee.power.crm.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_EMAIL;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_PHONE;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Lead")
class LeadTest {

  @Test
  @DisplayName("Проверка Объект равен сам себе")
  void shouldBeReflexiveWhenEqualsCalledOnSameObject() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead lead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW");

    // Then: Объект равен сам себе (isEqualTo использует equals() внутри)
    assertThat(lead).isEqualTo(lead);
  }

  @Test
  @DisplayName("Проверка Симметричность — порядок сравнения не важен")
  void shouldBeSymmetricWhenEqualsCalledOnTwoObjects() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead secondLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    // Then: Симметричность — порядок сравнения не важен
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(firstLead);
  }

  @Test
  @DisplayName("Проверка Транзитивность — если A=B и B=C, то A=C")
  void shouldBeTransitiveWhenEqualsChainOfThreeObjects() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead secondLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead thirdLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    // Then: Транзитивность — если A=B и B=C, то A=C
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(thirdLead);
    assertThat(firstLead).isEqualTo(thirdLead);
  }

  @Test
  @DisplayName("Проверка Результат одинаковый при многократных вызовах")
  void shouldBeConsistentWhenEqualsCalledMultipleTimes() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead secondLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    // Then: Результат одинаковый при многократных вызовах
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
  }

  @Test
  @DisplayName("Проверка Объект не равен null (isNotEqualTo проверяет equals(null) = false)")
  void shouldReturnFalseWhenEqualsComparedWithNull() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    // Then: Объект не равен null (isNotEqualTo проверяет equals(null) = false)
    assertThat(firstLead).isNotEqualTo(null);
  }

  @Test
  @DisplayName("Проверка Если объекты равны, то hashCode должен быть одинаковым")
  void shouldHaveSameHashCodeWhenObjectsAreEqual() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead secondLead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    int firstLeadHashCode = firstLead.hashCode();
    int secondLeadHashCode = secondLead.hashCode();

    // Then: Если объекты равны, то hashCode должен быть одинаковым
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLeadHashCode).isEqualTo(secondLeadHashCode);
  }

  @Test
  @DisplayName("Проверка Разные id = разные объекты (isNotEqualTo использует equals() внутри)")
  void shouldNotBeEqualWhenIdsAreDifferent() {
    // Given
    String idFirst = String.valueOf(UUID.randomUUID());
    String idSecond = String.valueOf(UUID.randomUUID());
    Lead firstLead = new Lead(idFirst, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");
    Lead secondLead = new Lead(idSecond, DEFAULT_EMAIL, DEFAULT_PHONE,
        "TestCompany", "NEW");

    // Then: Разные id = разные объекты (isNotEqualTo использует equals() внутри)
    assertThat(firstLead).isNotEqualTo(secondLead);
  }
}