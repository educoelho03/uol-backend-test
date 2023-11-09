package br.com.testbackend.javaspring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity threatNoSuchElementException(NoSuchFieldException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Essa lista não possui mais usuarios disponiveis", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
