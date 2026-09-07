package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DirectoryEntryDateType {
  FECHA_CREACION("FechaCreacion"),

  FECHA_MODIFICACION("FechaModificacion");

  private final String value;
}
