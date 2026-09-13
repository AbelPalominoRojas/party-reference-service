package com.ironman.partyreference.expose.exception;

import com.ironman.partyreference.application.exception.ApplicationException;
import com.ironman.partyreference.application.exception.ExceptionCatalog;
import com.ironman.partyreference.application.exception.ExceptionDetail;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionNormalized {

  public static List<ExceptionDetail> from(Throwable throwable) {
    if (throwable instanceof ApplicationException ex) {
      return ex.getDetails();
    }

    return ExceptionCatalog.APPLICATION_ERROR.buildException().getDetails();
  }
}
