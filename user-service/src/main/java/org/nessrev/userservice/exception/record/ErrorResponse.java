package org.nessrev.userservice.exception.record;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
  String message,
  List<FieldError> errors,
  LocalDateTime timestamp
) {
}