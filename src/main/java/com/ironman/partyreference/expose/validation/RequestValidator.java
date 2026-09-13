package com.ironman.partyreference.expose.validation;

import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.exception.ExceptionDetail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

/**
 * Punto único de validación manual para queries y mutations. No se anota `@Valid`/`@NotBlank`
 * directo en los parámetros de un `@Query`/`@Mutation`: SmallRye GraphQL valida los argumentos
 * automáticamente antes de invocar el método y arma su propio GraphQLError ("violations" /
 * classification: "ValidationError") sin pasar por ErrorExtensionProvider, así que ese error queda
 * fuera de nuestro formato uniforme. Validando manualmente y lanzando ApplicationException, el
 * error fluye por el mismo pipeline que cualquier otra excepción de negocio.
 */
@RequiredArgsConstructor
@ApplicationScoped
public class RequestValidator {

  private final Validator validator;

  public void validate(Object request) {
    Set<ConstraintViolation<Object>> violations = validator.validate(request);
    if (!violations.isEmpty()) {
      var details = violations.stream().map(RequestValidator::toExceptionDetail).toList();
      throw ExceptionCatalog.VALIDATION_ERROR.buildException(details);
    }
  }

  private static ExceptionDetail toExceptionDetail(ConstraintViolation<Object> violation) {
    String description = violation.getPropertyPath() + ": " + violation.getMessage();
    return new ExceptionDetail(ExceptionCatalog.VALIDATION_ERROR.getCode(), description);
  }
}
