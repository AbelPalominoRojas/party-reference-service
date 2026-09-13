package com.ironman.partyreference.application.exception;

import com.ironman.partyreference.application.exception.ApplicationException.ExceptionType;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCatalog {
  DATABASE_ERROR(
      "PRS0001",
      ExceptionType.INTERNAL_SERVER_ERROR,
      "An unexpected error occurred in the database service."),
  APPLICATION_ERROR(
      "PRS0002",
      ExceptionType.INTERNAL_SERVER_ERROR,
      "An unexpected error occurred, please try again later."),
  CUSTOMER_NOT_FOUND(
      "PRS0003", ExceptionType.NOT_FOUND, "Customer not found for document number: %s"),
  VALIDATION_ERROR("PRS0004", ExceptionType.BAD_REQUEST, "One or more fields are invalid.");

  private final String code;
  private final ExceptionType exceptionType;
  private final String detail;

  public ApplicationException buildException(Object... args) {
    var details = List.of(new ExceptionDetail(code, String.format(detail, args)));
    return buildException(details);
  }

  public ApplicationException buildException(List<ExceptionDetail> details) {
    return new ApplicationException(exceptionType.getGenericMessage(), exceptionType, details);
  }
}
