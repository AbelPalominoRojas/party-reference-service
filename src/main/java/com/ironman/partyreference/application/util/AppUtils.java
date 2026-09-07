package com.ironman.partyreference.application.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

  public static boolean isNullOrBlank(String value) {
    return value == null || value.isBlank();
  }

  public static String joinNonBlank(String delimiter, String... parts) {
    return Stream.of(parts)
        .filter(part -> !isNullOrBlank(part))
        .collect(Collectors.joining(delimiter));
  }
}
