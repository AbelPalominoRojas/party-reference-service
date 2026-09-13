package com.ironman.partyreference.application.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
  private final String code;
  private final ExceptionType exceptionType;
  private final List<String> details;

  public ApplicationException(String code, ExceptionType exceptionType, String detail) {
    this(code, exceptionType, List.of(detail));
  }

  public ApplicationException(String code, ExceptionType exceptionType, List<String> details) {
    super(exceptionType.getGenericMessage());
    this.code = code;
    this.exceptionType = exceptionType;
    this.details = details;
  }

  @Getter
  public enum ExceptionType {
    BAD_REQUEST(400, "Invalid input data. Verify format and values."),
    NOT_FOUND(404, "Resource not found. Verify the identifier."),
    CONFLICT(
        409, "Business rule violation. Verify the data does not conflict with existing records."),
    INTERNAL_SERVER_ERROR(500, "Internal server error. Contact administrator.");

    private final int statusCode;
    private final String genericMessage;

    ExceptionType(int statusCode, String genericMessage) {
      this.statusCode = statusCode;
      this.genericMessage = genericMessage;
    }
  }
}
