package ru.mentee.power.crm.storage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;

class LeadStorageTest {

  private static final String CITY = "Klin";
  private static final String STREET = "Lenina";
  private static final String ZIP = "141601";
  private static final String EMAIL = "test@example.com";
  private static final String PHONE = "+71234567890";
  private static Contact contact;

  @BeforeAll
  static void setUp() {
    Address address = new Address(CITY, STREET, ZIP);
    contact = new Contact(EMAIL, PHONE, address);
  }

  @Test
  void shouldAddLeadWhenLeadIsUnique() {
    // Given
    LeadStorage storage = new LeadStorage();
    Lead uniqueLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // When
    boolean added = storage.add(uniqueLead);

    // Then
    assertThat(added).isTrue();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(uniqueLead);
  }

  @Test
  void shouldRejectDuplicateWhenUUIDAlreadyExists() {
    // Given
    LeadStorage storage = new LeadStorage();
    UUID uuid = UUID.randomUUID();
    Lead existingLead = new Lead(uuid, contact, "TechCorp", "NEW");
    Lead duplicateLead = new Lead(uuid, contact, "Other", "NEW");
    storage.add(existingLead);

    // When
    boolean added = storage.add(duplicateLead);

    // Then
    assertThat(added).isFalse();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(existingLead);
  }

  @Test
  void shouldThrowExceptionWhenStorageIsFull() {
    // Given: Заполни хранилище 100 лидами
    LeadStorage storage = new LeadStorage();
    for (int index = 0; index < 100; index++) {
      storage.add(
          new Lead(UUID.randomUUID(), contact, "Company", "NEW"));
    }

    // When + Then: 101-й лид должен выбросить исключение
    Lead hundredFirstLead = new Lead(UUID.randomUUID(), contact, "Company", "NEW");

    assertThatThrownBy(() -> storage.add(hundredFirstLead))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("Storage is full");
  }

  @Test
  void shouldReturnOnlyAddedLeadsWhenFindAllCalled() {
    // Given
    LeadStorage storage = new LeadStorage();
    Lead firstLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(UUID.randomUUID(), contact, "StartupLab", "NEW");
    storage.add(firstLead);
    storage.add(secondLead);

    // When
    Lead[] result = storage.findAll();

    // Then
    assertThat(result).hasSize(2);
    assertThat(result).containsExactly(firstLead, secondLead);
  }

}