package ru.mentee.power.crm.storage;

import ru.mentee.power.crm.domain.Lead;

public class LeadStorage {

  private final Lead[] leads = new Lead[100];

  public boolean add(Lead lead) {
    // 1. Проверь дубликат: пройди по цикл по массиву leads
    for (Lead value : leads) {
      if (value != null && value.id().equals(lead.id())) {
        return false;
      }
    }

    // 2. Найди первую свободную ячейку пройди цикл по массиву leads
    for (int index = 0; index < leads.length; index++) {
      if (leads[index] == null) {
        leads[index] = lead;
        return true;
      }
    }

    // 3. Если цикл завершился и свободной ячейки нет
    throw new IllegalStateException("Storage is full");
  }

  public Lead[] findAll() {
    // 1.Посчитай количество не нулевых элементов
    int count = 0;
    for (Lead lead : leads) {
      if (lead != null) {
        count++;
      }
    }

    // 2. Создай новый массив размером count
    Lead[] result = new Lead[count];

    // 3. Заполни result ненулевыми элементами
    int resultIndex = 0;
    for (Lead lead : leads) {
      if (lead != null) {
        result[resultIndex++] = lead;
      }
    }

    // 4. Верни result
    return result;
  }

  public int size() {
    int count = 0;
    for (Lead lead : leads) {
      if (lead != null) {
        count++;
      }
    }
    return count;
  }

}
