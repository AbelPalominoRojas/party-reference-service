package com.ironman.partyreference.application.exception;

import com.ironman.partyreference.application.exception.ApplicationException.ExceptionType;
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
      "An unexpected error occurred, please try again later.");

  private final String code;
  private final ExceptionType exceptionType;
  private final String message;

  public ApplicationException buildException(Object... args) {
    String formattedMessage = String.format(message, args);

    return ApplicationException.builder()
        .code(code)
        .exceptionType(exceptionType)
        .message(formattedMessage)
        .build();
  }
}
