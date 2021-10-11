package io.turntabl.ecommerceapitrail.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(apiException(e.getMessage(), badRequest), badRequest);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(apiException(e.getMessage(), notFound), notFound);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        return new ResponseEntity<>(apiException(e.getMessage(), conflict), conflict);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<Object> NotAcceptableException(NotAcceptableException e){
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        return new ResponseEntity<>(apiException(e.getMessage(), notAcceptable), notAcceptable);
    }

    private ApiException apiException(String message, HttpStatus httpStatus){
        return new ApiException(message, httpStatus, httpStatus.value(), ZonedDateTime.now(ZoneId.of("Z")));
    }


}
