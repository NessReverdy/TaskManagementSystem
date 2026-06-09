package org.nessrev.userservice.exception.record;

public record FieldError(
  String field,
  String message
) {
}