package com.ironman.partyreference.application.business.impl;

import com.ironman.partyreference.application.business.CustomerService;
import com.ironman.partyreference.application.exception.ApplicationException;
import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.mapper.CustomerMapper;
import com.ironman.partyreference.application.model.api.PartyReferenceRetrieve;
import com.ironman.partyreference.application.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public Optional<PartyReferenceRetrieve> partyReferenceByDocumentNumber(String documentNumber) {
    try {
      return customerRepository
          .findByDocumentNumber(documentNumber)
          .map(customerMapper::toRetrieveResponse);
    } catch (ApplicationException ex) {
      throw ex;
    } catch (Exception ex) {
      log.error("CustomerServiceImpl:partyReferenceByDocumentNumber {}", ex.getMessage(), ex);
      throw ExceptionCatalog.APPLICATION_ERROR.buildException();
    }
  }
}
