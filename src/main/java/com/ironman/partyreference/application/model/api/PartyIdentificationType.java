package com.ironman.partyreference.application.model.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PartyIdentificationType implements ValueEnum {
  CARNE_EXTRANJERIA("CarneExtranjeria"),

  CARNE_IDENTIDAD("CarneIdentidad"),

  DOCUMENTO_NACIONAL_IDENTIDAD("DocumentoNacionalIdentidad"),

  IDENTIFICADOR_FICTICIO("IdentificadorFicticio"),

  IDENTIFICADOR_FICTICIO_MIGRACION("IdentificadorFicticioMigracion"),

  LIBRETA_TRIBUTARIA("LibretaTributaria"),

  PASAPORTE("Pasaporte"),

  REGISTRO_UNICO_CONTRIBUYENTE("RegistroUnicoContribuyente");

  private final String value;
}
