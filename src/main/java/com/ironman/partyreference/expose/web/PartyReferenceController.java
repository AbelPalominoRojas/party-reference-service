package com.ironman.partyreference.expose.web;

import com.ironman.partyreference.application.business.CustomerService;
import com.ironman.partyreference.application.model.api.*;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

@RequiredArgsConstructor
@GraphQLApi
public class PartyReferenceController {

  private final CustomerService customerService;

  @Query("partyReference")
  @Description("Consultar un cliente por su identificador")
  public PartyReferenceRetrieve partyReferenceByDocumentNumber(
      @Name("documentNumber") String documentNumber) {
    return customerService.partyReferenceByDocumentNumber(documentNumber).orElse(null);
  }
}
