package org.nessrev.taskmanagementsystem.exception.record;

public record FieldError(
        String field,
        String message
) {}