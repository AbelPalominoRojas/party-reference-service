package com.ironman.partyreference.application.business;

import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;
import java.util.Optional;

public interface CustomerService {

  Optional<PartyReferenceRetrieve> partyReferenceByDocumentNumber(String documentNumber);
}
