package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyType implements ValueEnum {
  PERSON("Person"),

  ORGANISATION("Organisation");

  private final String value;
}
