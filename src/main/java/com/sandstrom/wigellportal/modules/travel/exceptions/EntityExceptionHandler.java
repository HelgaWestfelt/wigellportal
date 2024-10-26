package com.sandstrom.wigellportal.modules.travel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class EntityExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler
    public ResponseEntity<EntityError> exceptionHandler(IllegalArgumentException exception) {
        EntityError entityError = new EntityError();
        entityError.setStatus(HttpStatus.BAD_REQUEST.value());
        entityError.setMessage(exception.getMessage());
        entityError.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(entityError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<EntityError> exceptionHandler(EntityNotFoundException entityNotFoundException) {
        EntityError entityError = new EntityError();
        entityError.setStatus(HttpStatus.NOT_FOUND.value());
        entityError.setMessage(entityNotFoundException.getMessage());
        entityError.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(entityError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityError> exceptionHandler(Exception exception) {
        EntityError entityError = new EntityError();
        entityError.setStatus(HttpStatus.BAD_REQUEST.value());
        entityError.setMessage("Något gick fel. Kontrollera inmatning av adress.");
        entityError.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(entityError, HttpStatus.BAD_REQUEST);
    }
}