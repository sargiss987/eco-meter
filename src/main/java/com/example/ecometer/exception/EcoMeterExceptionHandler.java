package com.example.ecometer.exception;

import com.example.ecometer.constants.EcoMeterConstants;
import com.example.ecometer.dto.ErrorResponse;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class EcoMeterExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex) {

    String errorMessage =
        ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));

    ErrorResponse errorResponse =
        new ErrorResponse().setMessage(EcoMeterConstants.VALIDATION_ERROR + errorMessage);

    log.error(errorMessage);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EcoMeterException.class)
  public ResponseEntity<ErrorResponse> handleEcoMeterException(EcoMeterException ex) {
    ErrorResponse errorResponse = new ErrorResponse().setMessage(ex.getMessage());

    log.error(ex.getMessage());

    return new ResponseEntity<>(errorResponse, ex.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    ErrorResponse errorResponse =
        new ErrorResponse().setMessage(EcoMeterConstants.INTERNAL_SERVER_ERROR);

    log.error(ex.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
