package ru.mentee.power.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactLombok {

  private String firstName;
  private String lastName;
  private String email;
}
