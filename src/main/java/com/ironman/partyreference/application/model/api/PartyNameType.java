package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyNameType {
  NOMBRE("Nombre"),

  APELLIDO_PATERNO("ApellidoPaterno"),

  APELLIDO_MATERNO("ApellidoMaterno"),

  RAZON_SOCIAL("RazonSocial"),

  NOMBRE_FANTASIA("NombreFantasia"),

  NOMBRE_COMPLETO("NombreCompleto");

  private final String value;
}
