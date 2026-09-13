package com.ironman.partyreference.application.repository;

import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.model.entity.CustomerEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<CustomerEntity, Long> {

  public Optional<CustomerEntity> findByDocumentNumber(String documentNumber) {
    try {
      return find("documentNumber", documentNumber).firstResultOptional();
    } catch (Exception e) {
      log.error("CustomerRepository:findByDocumentNumber {}", e.getMessage(), e);
      throw ExceptionCatalog.DATABASE_ERROR.buildException();
    }
  }
}
