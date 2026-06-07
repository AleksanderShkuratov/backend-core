package ru.mentee.power.crm.storage;

import ru.mentee.power.crm.domain.Lead;

public class LeadStorage {

  private Lead[] leads = new Lead[100];

  public boolean add(Lead lead) {
    // 1. Проверь дубликат: пройди по цикл по массиву leads
    for (int index = 0; index < leads.length; index++) {
      if (leads[index] != null && leads[index].email().equals(lead.email())) {
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
    for (int index = 0; index < leads.length; index++) {
      if (leads[index] != null) {
        count++;
      }
    }

    // 2. Создай новый массив размером count
    Lead[] result = new Lead[count];

    // 3. Заполни result ненулевыми элементами
    int resultIndex = 0;
    for (int index = 0; index < leads.length; index++) {
      if (leads[index] != null) {
        result[resultIndex++] = leads[index];
      }
    }

    // 4. Верни result
    return result;
  }

  public int size() {
    int count = 0;
    for (int i = 0; i < leads.length; i++) {
      if (leads[i] != null) {
        count++;
      }
    }
    return count;
  }

}
