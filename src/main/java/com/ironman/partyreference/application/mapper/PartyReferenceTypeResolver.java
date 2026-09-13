package com.ironman.partyreference.application.mapper;

import static com.ironman.partyreference.application.config.PartyReferenceProperties.PartyReferenceType;
import static com.ironman.partyreference.application.util.AppUtils.isNullOrBlank;

import com.ironman.partyreference.application.config.PartyReferenceProperties;
import com.ironman.partyreference.application.model.api.PartyIdentificationType;
import com.ironman.partyreference.application.model.api.PartyType;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class PartyReferenceTypeResolver {

  private final PartyReferenceProperties properties;

  public PartyIdentificationType resolveIdentificationType(String identificationTypeCode) {
    var enumName = findConfiguredName(identificationTypeCode, properties.identificationTypes());
    return findEnumByName(enumName, PartyIdentificationType.values());
  }

  public PartyType resolvePartyType(String partyTypeCode) {
    var enumName = findConfiguredName(partyTypeCode, properties.partyTypes());
    return findEnumByName(enumName, PartyType.values());
  }

  private String findConfiguredName(String code, List<PartyReferenceType> configuredTypes) {
    if (isNullOrBlank(code)) {
      return null;
    }
    return configuredTypes.stream()
        .filter(type -> code.equalsIgnoreCase(type.code()))
        .findFirst()
        .map(PartyReferenceType::name)
        .orElse(null);
  }

  private <E extends Enum<E>> E findEnumByName(String name, E[] enumValues) {
    if (isNullOrBlank(name)) {
      return null;
    }
    return Arrays.stream(enumValues)
        .filter(value -> name.equalsIgnoreCase(value.toString()))
        .findFirst()
        .orElse(null);
  }
}
