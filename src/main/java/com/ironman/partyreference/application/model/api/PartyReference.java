package com.ironman.partyreference.application.model.api;

import java.util.List;

public record PartyReference(
    String partyId, PartyIdentification partyIdentification, List<PartyName> partyNames) {}
