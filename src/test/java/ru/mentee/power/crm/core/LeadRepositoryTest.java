package ru.mentee.power.crm.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mentee.power.crm.util.TestObjectFactory.createLead;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Lead;

@DisplayName("Тестирование LeadRepository")
class LeadRepositoryTest {

  @Test
  @DisplayName("Should automatically deduplicate leads by id")
  void shouldDeduplicateLeadsById() {
    // Given
    LeadRepository repository = new LeadRepository();
    Lead firstLead = createLead();

    // When
    boolean addFirst = repository.add(firstLead);
    boolean addSecond = repository.add(firstLead);

    // Then
    Set<Lead> allLeads = repository.findAll();
    assertThat(allLeads).hasSize(1);
    assertTrue(allLeads.contains(firstLead));
    assertTrue(addFirst);
    assertFalse(addSecond);
  }

  @Test
  @DisplayName("Should allow different leads with different ids")
  void shouldAllowDifferentLeads() {
    // Given
    LeadRepository repository = new LeadRepository();
    Lead firstLead = createLead();
    Lead secondLead = createLead();
    assertThat(firstLead.id()).isNotEqualTo(secondLead.id());

    // When
    boolean addFirst = repository.add(firstLead);
    boolean addSecond = repository.add(secondLead);

    // Then
    Set<Lead> allLeads = repository.findAll();
    assertThat(allLeads).hasSize(2);
    assertTrue(allLeads.contains(firstLead));
    assertTrue(allLeads.contains(secondLead));
    assertTrue(addFirst);
    assertTrue(addSecond);
  }

  @Test
  @DisplayName("Should find existing lead through contains")
  void shouldFindExistingLead() {
    // Given
    LeadRepository repository = new LeadRepository();
    Lead firstLead = createLead();
    repository.add(firstLead);

    // When
    boolean containsFirst = repository.contains(firstLead);

    // Then
    assertTrue(containsFirst);
  }

  @Test
  @DisplayName("Should return unmodifiable set from findAll")
  void shouldReturnUnmodifiableSet() {
    // Given
    LeadRepository repository = new LeadRepository();
    Lead firstLead = createLead();
    Lead secondLead = createLead();
    repository.add(firstLead);

    // When
    Set<Lead> allLeads = repository.findAll();

    // Then
    assertThatThrownBy(() -> allLeads.add(secondLead))
        .isInstanceOf(UnsupportedOperationException.class);
    assertThatThrownBy(allLeads::clear)
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  @DisplayName("При Lead=null добавление или проверка нахождения в repository "
      + "бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenLeadIsNull() {
    // Given
    LeadRepository repository = new LeadRepository();
    // Then
    assertThatThrownBy(() -> repository.add(null)).isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> repository.contains(null))
        .isInstanceOf(IllegalArgumentException.class);
  }

}