package ru.mentee.power.crm.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mentee.power.crm.util.TestObjectFactory.createContact;
import static ru.mentee.power.crm.util.TestObjectFactory.createLead;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование Lead")
class InMemoryLeadRepositoryTest {

  @Test
  @DisplayName("Проверка добавления Lead в пустой репозиторий")
  void shouldAddLeadToEmptyRepository() {
    // Given
    InMemoryLeadRepository repository = new InMemoryLeadRepository();
    Lead firstLead = createLead();

    // When
    repository.add(firstLead);

    // Then
    List<Lead> allLeads = repository.findAll();
    assertThat(allLeads).hasSize(1);
    assertThat(allLeads.getFirst()).isEqualTo(firstLead);

    Optional<Lead> foundLeadOpt = repository.findById(firstLead.id());
    assertThat(foundLeadOpt).isPresent();
    Lead foundLead = foundLeadOpt.get();
    assertEquals(foundLead, firstLead);
  }

  @Test
  @DisplayName("findById для несуществующего ID возвращает Optional.empty()")
  void shouldReturnEmptyOptionalWhenLeadNotFound() {
    // Given
    UUID idNotLead = UUID.randomUUID();
    List<UUID> ids = new ArrayList<>();
    InMemoryLeadRepository repository = new InMemoryLeadRepository();

    for (int i = 0; i < 10; i++) {
      Lead lead = createLead();
      repository.add(lead);
      ids.add(lead.id());
    }

    assertThat(ids).doesNotContain(idNotLead);

    // When
    Optional<Lead> result = repository.findById(idNotLead);

    // Then
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("add: попытка добавить Lead с существующим UUID игнорируется")
  void shouldNotAddDuplicateLeadWithSameUuid() {
    // Given
    Lead firstLead = createLead();
    UUID idFirstLead = firstLead.id();
    Lead duplicateLead = createLead(idFirstLead, createContact(), "TestCompany", "NEW");

    InMemoryLeadRepository repository = new InMemoryLeadRepository();
    repository.add(firstLead);

    // When
    repository.add(duplicateLead);

    // Then
    assertThat(repository.findAll()).hasSize(1);
    assertThat(repository.findAll()).contains(firstLead);
  }

  @Test
  @DisplayName("remove: удаление существующего лида по ID")
  void shouldRemoveExistingLeadById() {
    // Given
    List<UUID> ids = new ArrayList<>();
    InMemoryLeadRepository repository = new InMemoryLeadRepository();

    for (int i = 0; i < 5; i++) {
      Lead lead = createLead();
      repository.add(lead);
      ids.add(lead.id());
    }

    UUID idRemoveLead = ids.get(2);
    assertThat(repository.findAll()).hasSize(5);
    Optional<Lead> foundLead = repository.findById(idRemoveLead);
    assertThat(foundLead).isPresent();
    assertThat(idRemoveLead).isEqualTo(foundLead.get().id());

    // When
    repository.remove(idRemoveLead);

    // Then
    assertThat(repository.findAll()).hasSize(4);
    Optional<Lead> result = repository.findById(idRemoveLead);
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("findAll: возвращает защитную копию, изменения не влияют на storage")
  void findAllShouldReturnDefensiveCopy() {
    // Given
    InMemoryLeadRepository repository = new InMemoryLeadRepository();

    for (int i = 0; i < 5; i++) {
      Lead lead = createLead();
      repository.add(lead);
    }

    // When
    List<Lead> returnedList = repository.findAll();
    returnedList.clear();

    List<Lead> returnedList2 = repository.findAll();
    returnedList2.add(createLead());
    assertEquals(6, returnedList2.size());

    List<Lead> returnedList3 = repository.findAll();
    returnedList3.removeFirst();
    assertEquals(4, returnedList3.size());

    List<Lead> returnedList4 = repository.findAll();
    Lead originalLead = returnedList4.getFirst();
    Lead modifiedLead = new Lead(
        originalLead.id(),  // тот же ID
        originalLead.contact(), // те же контакты
        originalLead.company(), // та же компания
        "CLOSED"           // другой статус
    );
    returnedList4.set(0, modifiedLead);

    // Then
    assertThat(repository.findAll())
        .hasSize(5)
        .contains(originalLead);
  }

  @Test
  @DisplayName("Добавление Lead=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenLeadIsNull() {
    // Given
    InMemoryLeadRepository repository = new InMemoryLeadRepository();
    // Then
    assertThatThrownBy(() -> repository.add(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Удаление или поиск с id=null бросает исключение IllegalArgumentException")
  void shouldThrowExceptionWhenIdIsNull() {
    // Given
    InMemoryLeadRepository repository = new InMemoryLeadRepository();

    // Then
    assertThatThrownBy(() -> repository.remove(null))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> repository.findById(null))
        .isInstanceOf(IllegalArgumentException.class);
  }

}