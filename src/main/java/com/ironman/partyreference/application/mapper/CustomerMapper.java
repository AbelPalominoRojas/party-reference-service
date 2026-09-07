package com.ironman.partyreference.application.mapper;

import static com.ironman.partyreference.application.mapper.PartyReferenceBuilder.*;
import static com.ironman.partyreference.application.model.api.DirectoryEntryDateType.FECHA_CREACION;
import static com.ironman.partyreference.application.model.api.DirectoryEntryDateType.FECHA_MODIFICACION;
import static com.ironman.partyreference.application.util.Constants.CUSTOMER_TYPE_NATURAL_PERSON;

import com.ironman.partyreference.application.model.api.*;
import com.ironman.partyreference.application.model.entity.CustomerEntity;
import java.util.List;
import org.mapstruct.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.JAKARTA_CDI,
    uses = {PartyReferenceTypeResolver.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

  @Mapping(target = "partyReference", source = ".")
  @Mapping(target = "partyType", source = "customerType")
  @Mapping(target = "directoryEntryDates", source = ".", qualifiedByName = "mapDirectoryEntryDates")
  PartyReferenceRetrieve toRetrieveResponse(CustomerEntity customer);

  @Mapping(target = "partyId", source = "id")
  @Mapping(target = "partyIdentification", source = ".")
  @Mapping(target = "partyNames", source = ".", qualifiedByName = "mapPartyNamesFromCustomer")
  PartyReference toPartyReferenceWithId(CustomerEntity customer);

  @Mapping(target = "partyIdentificationType", source = "documentType")
  @Mapping(target = "partyIdentification", source = ".")
  PartyIdentification toPartyIdentification(CustomerEntity customer);

  @Mapping(target = "identifierValue", source = "documentNumber")
  Identifier toIdentifier(CustomerEntity customer);

  @Named("mapPartyNamesFromCustomer")
  default List<PartyName> mapPartyNamesFromCustomer(CustomerEntity customer) {
    if (CUSTOMER_TYPE_NATURAL_PERSON.equalsIgnoreCase(customer.getCustomerType())) {
      return buildNaturalPersonNames(customer);
    }
    return buildOrganizationNames(customer);
  }

  @Named("mapDirectoryEntryDates")
  default List<DirectoryEntryDate> mapDirectoryEntryDates(CustomerEntity customer) {
    return List.of(
        buildDirectoryEntryDate(FECHA_CREACION, customer.getCreatedAt()),
        buildDirectoryEntryDate(FECHA_MODIFICACION, customer.getUpdatedAt()));
  }
}
