package com.example.ecometer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EcoMeterException extends RuntimeException {
  private final HttpStatus status;

  public EcoMeterException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
