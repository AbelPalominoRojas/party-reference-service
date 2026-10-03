package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyIdentificationType implements ValueEnum {
  IDENTITYCARDNUMBER("Identitycardnumber"),

  NATIONALREGISTRATIONIDENTIFICATIONNUMBER("Nationalregistrationidentificationnumber"),

  PASSPORTNUMBER("Passportnumber"),

  REGISTRATIONAUTHORITYIDENTIFICATION("Registrationauthorityidentification");

  private final String value;
}
