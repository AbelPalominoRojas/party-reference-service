package com.ironman.partyreference.application.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;
import java.util.List;

@ConfigMapping(prefix = "party-reference")
public interface PartyReferenceProperties {

  @WithName("identification-types")
  List<PartyReferenceType> identificationTypes();

  @WithName("party-types")
  List<PartyReferenceType> partyTypes();

  interface PartyReferenceType {
    @WithName("code")
    String code();

    @WithName("name")
    String name();
  }
}
