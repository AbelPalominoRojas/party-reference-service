package com.ironman.partyreference.application.exception;

import java.util.List;
import lombok.Getter;

/**
 * Normaliza cualquier Throwable a {code, details} para poblar extensions.exceptionDetails, aun
 * cuando la excepción no sea una ApplicationException (defensivo, no debería ocurrir en flujo
 * normal ya que la capa de servicio envuelve todo en ApplicationException).
 */
@Getter
public class NormalizedError {

  private final String code;
  private final List<String> details;

  private NormalizedError(String code, List<String> details) {
    this.code = code;
    this.details = details;
  }

  public static NormalizedError from(Throwable throwable) {
    if (throwable instanceof ApplicationException ex) {
      return new NormalizedError(ex.getCode(), ex.getDetails());
    }
    ApplicationException fallback = ExceptionCatalog.APPLICATION_ERROR.buildException();
    return new NormalizedError(fallback.getCode(), fallback.getDetails());
  }
}
