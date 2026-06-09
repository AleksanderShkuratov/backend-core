package ru.mentee.power.crm.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_EMAIL;
import static ru.mentee.power.crm.util.TestObjectFactory.DEFAULT_PHONE;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.model.Lead;

@DisplayName("Тестирование LeadRepository")
class LeadRepositoryTest {

  private LeadRepository repository;

  @BeforeEach
  void setUp() {
    repository = new LeadRepository();
  }

  @Test
  @DisplayName("findById: поиск существующего Lead по ID")
  void shouldSaveAndFindLeadByIdWhenLeadSaved() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead lead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW");

    // When
    repository.save(lead);

    // Then
    assertThat(repository.findById(id)).isNotNull();
  }

  @Test
  @DisplayName("findById: поиск существующего Lead по ID в пустом repository")
  void shouldReturnNullWhenLeadNotFound() {
    // Given
    String id = String.valueOf(UUID.randomUUID());

    // Then
    assertThat(repository.findById(id)).isNull();
  }

  @Test
  @DisplayName("findAll: проверка возвращения всех значений из repository")
  void shouldReturnAllLeadsWhenMultipleLeadsSaved() {
    // Given
    String idFirst = String.valueOf(UUID.randomUUID());
    String idSecond = String.valueOf(UUID.randomUUID());
    String idThird = String.valueOf(UUID.randomUUID());

    repository.save(
        new Lead(idFirst, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));
    repository.save(
        new Lead(idSecond, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));
    repository.save(
        new Lead(idThird, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));

    // Then
    assertThat(repository.findAll()).hasSize(3);
  }

  @Test
  @DisplayName("delete: проверка удаления существующего Lead в repository")
  void shouldDeleteLeadWhenLeadExists() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead lead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW");
    repository.save(lead);

    // When
    repository.delete(id);

    // Then
    assertThat(repository.findById(id)).isNull();
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  @DisplayName("Проверка замены данных сохранении у существующего Lead в repository")
  void shouldOverwriteLeadWhenSavedWithSameId() {
    // Given
    String id = String.valueOf(UUID.randomUUID());
    Lead lead = new Lead(id, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW");
    repository.save(lead);

    // When
    Lead leadNewEmail = new Lead(id, "sale@gmail.com", DEFAULT_PHONE,
        "TestCompany", "NEW");
    repository.save(leadNewEmail);

    // Then
    assertThat(repository.findById(id))
        .isNotNull()
        .isEqualTo(leadNewEmail);
    assertThat(repository.findAll()).hasSize(1);
  }

  @Test
  @DisplayName("Проверка метода size()")
  void shouldReturnSizeLeadsWhenMultipleLeadsSaved() {
    // Given
    String idFirst = String.valueOf(UUID.randomUUID());
    String idSecond = String.valueOf(UUID.randomUUID());
    String idThird = String.valueOf(UUID.randomUUID());

    repository.save(
        new Lead(idFirst, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));
    repository.save(
        new Lead(idSecond, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));
    repository.save(
        new Lead(idThird, DEFAULT_EMAIL, DEFAULT_PHONE, "TestCompany", "NEW"));
    assertThat(repository.findAll()).hasSize(3);

    // Then
    assertThat(repository.size()).isEqualTo(3);
  }
}