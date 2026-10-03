package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyNameType {
  NAME("Name"),

  LAST_NAME_PATERNAL("LastNamePaternal"),

  LAST_NAME_MATERNAL("LastNameMaternal"),

  LEGAL_NAME("LegalName"),

  TRADE_NAME("TradeName"),

  FULL_NAME("FullName");

  private final String value;
}
