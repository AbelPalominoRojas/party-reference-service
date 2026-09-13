package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyType implements ValueEnum {
  PERSONA("Persona"),

  ORGANIZACION("Organizacion");

  private final String value;
}
