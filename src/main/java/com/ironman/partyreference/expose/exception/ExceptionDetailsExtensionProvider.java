package com.ironman.partyreference.expose.exception;

import com.ironman.partyreference.application.exception.NormalizedError;
import io.smallrye.graphql.api.ErrorExtensionProvider;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonValue;

public class ExceptionDetailsExtensionProvider implements ErrorExtensionProvider {

  @Override
  public String getKey() {
    return "exceptionDetails";
  }

  @Override
  public JsonValue mapValueFrom(Throwable exception) {
    NormalizedError error = NormalizedError.from(exception);
    JsonArrayBuilder detailsBuilder = Json.createArrayBuilder();
    error
        .getDetails()
        .forEach(
            detail ->
                detailsBuilder.add(
                    Json.createObjectBuilder()
                        .add("code", error.getCode())
                        .add("description", detail)));
    return detailsBuilder.build();
  }
}
