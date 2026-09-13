package com.ironman.partyreference.application.exception;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {
  private final String message;
  private final ExceptionType exceptionType;
  private final List<ExceptionDetail> details;

  @RequiredArgsConstructor
  @Getter
  public enum ExceptionType {
    BAD_REQUEST(400, "Invalid input data. Verify format and values."),
    NOT_FOUND(404, "Resource not found. Verify the identifier."),
    CONFLICT(
        409, "Business rule violation. Verify the data does not conflict with existing records."),
    INTERNAL_SERVER_ERROR(500, "Internal server error. Contact administrator.");

    private final int statusCode;
    private final String genericMessage;
  }
}
