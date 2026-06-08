package ru.mentee.power.crm.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.mentee.power.crm.util.TestObjectFactory.createContact;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LeadEqualsHashCodeTest {
  
  private static Contact contact;

  @BeforeAll
  static void setUp() {
    contact = createContact();
  }

  @Test
  void shouldBeReflexiveWhenEqualsCalledOnSameObject() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // Then: Объект равен сам себе (isEqualTo использует equals() внутри)
    assertThat(lead).isEqualTo(lead);
  }

  @Test
  void shouldBeSymmetricWhenEqualsCalledOnTwoObjects() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead firstLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(uuid, contact, "TechCorp", "NEW");

    // Then: Симметричность — порядок сравнения не важен
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(firstLead);
  }

  @Test
  void shouldBeTransitiveWhenEqualsChainOfThreeObjects() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead firstLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead thirdLead = new Lead(uuid, contact, "TechCorp", "NEW");

    // Then: Транзитивность — если A=B и B=C, то A=C
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(thirdLead);
    assertThat(firstLead).isEqualTo(thirdLead);
  }

  @Test
  void shouldBeConsistentWhenEqualsCalledMultipleTimes() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead firstLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(uuid, contact, "TechCorp", "NEW");

    // Then: Результат одинаковый при многократных вызовах
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
  }

  @Test
  void shouldReturnFalseWhenEqualsComparedWithNull() {
    // Given
    Lead lead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // Then: Объект не равен null (isNotEqualTo проверяет equals(null) = false)
    assertThat(lead).isNotEqualTo(null);
  }

  @Test
  void shouldHaveSameHashCodeWhenObjectsAreEqual() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead firstLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(uuid, contact, "TechCorp", "NEW");
    int firstLeadHashCode = firstLead.hashCode();
    int secondLeadHashCode = secondLead.hashCode();

    // Then: Если объекты равны, то hashCode должен быть одинаковым
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLeadHashCode).isEqualTo(secondLeadHashCode);
  }

  @Test
  void shouldWorkInHashMapWhenLeadUsedAsKey() {
    // Given
    UUID uuid = UUID.randomUUID();
    Lead keyLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead lookupLead = new Lead(uuid, contact, "TechCorp", "NEW");

    Map<Lead, String> map = new HashMap<>();
    map.put(keyLead, "CONTACTED");

    // When: Получаем значение по другому объекту с тем же id
    String status = map.get(lookupLead);

    // Then: HashMap нашел значение благодаря equals/hashCode
    assertThat(status).isEqualTo("CONTACTED");
  }

  @Test
  void shouldNotBeEqualWhenIdsAreDifferent() {
    // Given
    Lead firstLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    Lead differentLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // Then: Разные id = разные объекты (isNotEqualTo использует equals() внутри)
    assertThat(firstLead).isNotEqualTo(differentLead);
  }

}
