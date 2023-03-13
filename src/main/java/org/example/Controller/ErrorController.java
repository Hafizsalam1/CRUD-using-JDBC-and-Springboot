package org.example.Controller;

import org.example.Exception.MaximumDataException;
import org.example.Exception.NotFoundException;
import org.example.Model.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new org.example.Model.Response.ErrorResponse("X06", exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("X01", exception.getMessage()));

    }

    @ExceptionHandler(MaximumDataException.class)
    public ResponseEntity<ErrorResponse> handleMaximumData(MaximumDataException exception){
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse("X03", exception.getMessage()));
    }


}
