package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DirectoryEntryDateType {
  OPEN_DATE("OpenDate"),

  REFRESH_DATE("RefreshDate");

  private final String value;
}
