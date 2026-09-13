package com.ironman.partyreference.application.model.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PartyReferenceQuery(@NotBlank @Size(max = 15) String documentNumber) {}
