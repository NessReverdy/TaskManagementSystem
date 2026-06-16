package org.nessrev.authservice.exception;

import org.nessrev.authservice.exception.custom.*;
import org.nessrev.authservice.exception.record.ErrorResponse;
import org.nessrev.authservice.exception.record.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponse(
        ex.getMessage(),
        List.of(),
        LocalDateTime.now()
      ));
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new ErrorResponse(
        ex.getMessage(),
        List.of(),
        LocalDateTime.now()
      ));
  }

  @ExceptionHandler(WrongPasswordException.class)
  public ResponseEntity<ErrorResponse> handleWrongPassword(WrongPasswordException ex) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponse(
        ex.getMessage(),
        List.of(),
        LocalDateTime.now()
      ));
  }

  @ExceptionHandler(InvalidRefreshTokenException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRefreshToken(InvalidRefreshTokenException ex) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponse(
        ex.getMessage(),
        List.of(),
        LocalDateTime.now()
      ));
  }

  @ExceptionHandler(TokenHashingException.class)
  public ResponseEntity<ErrorResponse> handleTokenHashing(TokenHashingException ex) {
    return ResponseEntity
      .status(HttpStatus.CONFLICT)
      .body(new ErrorResponse(
        ex.getMessage(),
        List.of(),
        LocalDateTime.now()
      ));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

    List<FieldError> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(err -> new FieldError(
        err.getField(),
        err.getDefaultMessage()
      ))
      .toList();

    return ResponseEntity
      .badRequest()
      .body(new ErrorResponse(
        "Validation failed",
        errors,
        LocalDateTime.now()
      ));
  }
}
