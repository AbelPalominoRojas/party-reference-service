package com.ironman.partyreference.expose.exception;

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
    JsonArrayBuilder detailsBuilder = Json.createArrayBuilder();

    ExceptionNormalized.from(exception)
        .forEach(
            detail ->
                detailsBuilder.add(
                    Json.createObjectBuilder()
                        .add("code", detail.code())
                        .add("description", detail.description())));

    return detailsBuilder.build();
  }
}
