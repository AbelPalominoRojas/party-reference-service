package com.ironman.partyreference.application.business.impl;

import com.ironman.partyreference.application.business.CustomerService;
import com.ironman.partyreference.application.mapper.CustomerMapper;
import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;
import com.ironman.partyreference.application.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public PartyReferenceRetrieve partyReferenceById(String partyId) {
    return customerRepository
        .findByIdOptional(Long.parseLong(partyId))
        .map(customerMapper::toRetrieveResponse)
        .orElse(null);
  }
}
