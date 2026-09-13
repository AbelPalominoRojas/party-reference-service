package com.ironman.partyreference.application.mapper;

import static com.ironman.partyreference.application.model.api.PartyNameType.*;
import static com.ironman.partyreference.application.util.AppUtils.joinNonBlank;

import com.ironman.partyreference.application.model.api.*;
import com.ironman.partyreference.application.model.entity.CustomerEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartyReferenceBuilder {

  public static List<PartyName> buildNaturalPersonNames(CustomerEntity customer) {
    var fullName =
        joinNonBlankNames(
            customer.getName(), customer.getPaternalSurname(), customer.getMaternalSurname());

    return List.of(
        createFirstNameEntry(customer.getName()),
        createPaternalSurnameEntry(customer.getPaternalSurname()),
        createMaternalSurnameEntry(customer.getMaternalSurname()),
        buildPartyName(fullName, NOMBRE_COMPLETO));
  }

  public static List<PartyName> buildOrganizationNames(CustomerEntity customer) {
    return List.of(
        createOrganizationNameEntry(customer.getName()),
        createTradeNameEntry(customer.getTradeName()));
  }

  public static DirectoryEntryDate buildDirectoryEntryDate(
      DirectoryEntryDateType entryDateType, LocalDateTime dateTime) {
    return new DirectoryEntryDate(entryDateType, dateTime);
  }

  private static PartyName createFirstNameEntry(String firstName) {
    return buildPartyName(firstName, NOMBRE);
  }

  private static PartyName createPaternalSurnameEntry(String paternalSurname) {
    return buildPartyName(paternalSurname, APELLIDO_PATERNO);
  }

  private static PartyName createMaternalSurnameEntry(String maternalSurname) {
    return buildPartyName(maternalSurname, APELLIDO_MATERNO);
  }

  private static PartyName createOrganizationNameEntry(String organizationName) {
    return buildPartyName(organizationName, RAZON_SOCIAL);
  }

  private static PartyName createTradeNameEntry(String tradeName) {
    return buildPartyName(tradeName, NOMBRE_FANTASIA);
  }

  private static String joinNonBlankNames(String... nameParts) {
    return joinNonBlank(" ", nameParts);
  }

  private static PartyName buildPartyName(String nameValue, PartyNameType nameType) {
    return new PartyName(nameType, nameValue);
  }
}
