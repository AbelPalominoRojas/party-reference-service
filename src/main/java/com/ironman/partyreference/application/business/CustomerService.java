package com.ironman.partyreference.application.business;

import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;

public interface CustomerService {
  PartyReferenceRetrieve partyReferenceById(String partyId);
}
