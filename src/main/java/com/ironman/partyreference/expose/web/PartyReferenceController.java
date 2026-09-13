package com.ironman.partyreference.expose.web;

import com.ironman.partyreference.application.business.CustomerService;
import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.model.api.PartyReferenceQuery;
import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;
import com.ironman.partyreference.expose.validation.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@RequiredArgsConstructor
@GraphQLApi
public class PartyReferenceController {

  private final CustomerService customerService;
  private final RequestValidator requestValidator;

  @Query("partyReference")
  @Description("Consultar un cliente por su identificador")
  public PartyReferenceRetrieve partyReferenceByDocumentNumber(
      @Name("documentNumber") String documentNumber) {
    requestValidator.validate(new PartyReferenceQuery(documentNumber));
    return customerService
        .partyReferenceByDocumentNumber(documentNumber)
        .orElseThrow(() -> ExceptionCatalog.CUSTOMER_NOT_FOUND.buildException(documentNumber));
  }
}
