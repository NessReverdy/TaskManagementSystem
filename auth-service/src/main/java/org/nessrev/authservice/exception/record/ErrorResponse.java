package org.nessrev.authservice.exception.record;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
  String message,
  List<FieldError> errors,
  LocalDateTime timestamp
) {
}