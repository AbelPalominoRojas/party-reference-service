package com.ironman.partyreference.application.model.api;

import java.util.List;

public record PartyReferenceRetrieve(
    PartyReference partyReference,
    PartyType partyType,
    List<DirectoryEntryDate> directoryEntryDates) {}
