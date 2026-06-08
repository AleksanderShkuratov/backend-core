package ru.mentee.power.crm.util;

import java.util.UUID;

import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;

public class TestObjectFactory {

  private static final String DEFAULT_CITY = "Klin";
  private static final String DEFAULT_STREET = "Lenina";
  private static final String DEFAULT_ZIP = "141601";
  private static final String DEFAULT_EMAIL = "test@example.com";
  private static final String DEFAULT_PHONE = "+71234567890";
  private static final String DEFAULT_COMPANY = "Company";
  private static final String DEFAULT_STATUS = "NEW";

  public static Address createAddress() {
    return createAddress(DEFAULT_CITY, DEFAULT_STREET, DEFAULT_ZIP);
  }

  public static Address createAddress(String city, String street, String zip) {
    return new Address(city, street, zip);
  }

  public static Contact createContact() {
    return createContact(DEFAULT_EMAIL, DEFAULT_PHONE, createAddress());
  }

  public static Contact createContact(String email, String phone, Address address) {
    return new Contact(email, phone, address);
  }

  public static Lead createLead() {
    return createLead(UUID.randomUUID(), createContact(), DEFAULT_COMPANY, DEFAULT_STATUS);
  }

  public static Lead createLead(UUID id, Contact contact, String company, String status) {
    return new Lead(id, contact, company, status);
  }

}
