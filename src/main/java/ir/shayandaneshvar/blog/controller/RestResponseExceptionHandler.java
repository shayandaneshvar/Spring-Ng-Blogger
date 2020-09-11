package ir.shayandaneshvar.blog.controller;

import ir.shayandaneshvar.blog.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFoundError(Exception e, WebRequest req) {
        return new ResponseEntity<>("Resource Not Found!", new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }
}
