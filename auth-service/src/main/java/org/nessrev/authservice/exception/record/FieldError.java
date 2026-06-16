package org.nessrev.authservice.exception.record;

public record FieldError(
  String field,
  String message
) {
}