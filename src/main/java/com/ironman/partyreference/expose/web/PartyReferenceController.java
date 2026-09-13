package com.ironman.partyreference.expose.web;

import com.ironman.partyreference.application.business.CustomerService;
import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.model.api.PartyReferenceQuery;
import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@RequiredArgsConstructor
@GraphQLApi
public class PartyReferenceController {

  private final CustomerService customerService;
  private final Validator validator;

  @Query("partyReference")
  @Description("Consultar un cliente por su identificador")
  public PartyReferenceRetrieve partyReferenceByDocumentNumber(
      @Name("documentNumber") String documentNumber) {
    validate(new PartyReferenceQuery(documentNumber));
    return customerService
        .partyReferenceByDocumentNumber(documentNumber)
        .orElseThrow(() -> ExceptionCatalog.CUSTOMER_NOT_FOUND.buildException(documentNumber));
  }

  private void validate(Object request) {
    Set<ConstraintViolation<Object>> violations = validator.validate(request);
    if (!violations.isEmpty()) {
      List<String> details =
          violations.stream()
              .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
              .toList();
      throw ExceptionCatalog.VALIDATION_ERROR.buildException(details);
    }
  }
}
