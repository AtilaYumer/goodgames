package com.konak.goodgames.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<Object> handleConflictExceptions(
      ConflictException exception, WebRequest webRequest) {
    ExceptionResponse response = new ExceptionResponse();
    response.setDateTime(LocalDateTime.now());
    response.setMessage(exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundExceptions(
      NotFoundException exception, WebRequest webRequest) {
    ExceptionResponse response = new ExceptionResponse();
    response.setDateTime(LocalDateTime.now());
    response.setMessage(exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}
