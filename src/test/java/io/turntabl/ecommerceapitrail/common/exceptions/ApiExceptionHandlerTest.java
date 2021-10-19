package io.turntabl.ecommerceapitrail.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApiExceptionHandlerTest {

    String exceptionMessage = "An error occurred";

    @Test
    void canHandleBadRequestException() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ResponseEntity<Object> actualHandleBadRequestExceptionResult = apiExceptionHandler
                .handleBadRequestException(new BadRequestException(exceptionMessage));
        assertTrue(actualHandleBadRequestExceptionResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleBadRequestExceptionResult.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((ApiException) actualHandleBadRequestExceptionResult.getBody()).getHttpStatus());
        assertEquals(exceptionMessage, ((ApiException) actualHandleBadRequestExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), ((ApiException) actualHandleBadRequestExceptionResult.getBody()).getStatus().intValue());
    }

    @Test
    void canHandleNotFoundException() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ResponseEntity<Object> actualHandleNotFoundExceptionResult = apiExceptionHandler
                .handleNotFoundException(new NotFoundException(exceptionMessage));
        assertTrue(actualHandleNotFoundExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleNotFoundExceptionResult.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, ((ApiException) actualHandleNotFoundExceptionResult.getBody()).getHttpStatus());
        assertEquals(exceptionMessage, ((ApiException) actualHandleNotFoundExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), ((ApiException) actualHandleNotFoundExceptionResult.getBody()).getStatus().intValue());
    }

    @Test
    void canHandleAlreadyExistException() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ResponseEntity<Object> actualHandleAlreadyExistExceptionResult = apiExceptionHandler
                .handleAlreadyExistException(new AlreadyExistException(exceptionMessage));
        assertTrue(actualHandleAlreadyExistExceptionResult.hasBody());
        assertEquals(HttpStatus.CONFLICT, actualHandleAlreadyExistExceptionResult.getStatusCode());
        assertEquals(HttpStatus.CONFLICT,
                ((ApiException) actualHandleAlreadyExistExceptionResult.getBody()).getHttpStatus());
        assertEquals(exceptionMessage, ((ApiException) actualHandleAlreadyExistExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), ((ApiException) actualHandleAlreadyExistExceptionResult.getBody()).getStatus().intValue());
    }

    @Test
    void canNotAcceptableException() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ResponseEntity<Object> actualNotAcceptableExceptionResult = apiExceptionHandler
                .NotAcceptableException(new NotAcceptableException(exceptionMessage));
        assertTrue(actualNotAcceptableExceptionResult.hasBody());
        assertEquals(HttpStatus.NOT_ACCEPTABLE, actualNotAcceptableExceptionResult.getStatusCode());
        assertEquals(HttpStatus.NOT_ACCEPTABLE,
                ((ApiException) actualNotAcceptableExceptionResult.getBody()).getHttpStatus());
        assertEquals(exceptionMessage, ((ApiException) actualNotAcceptableExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), ((ApiException) actualNotAcceptableExceptionResult.getBody()).getStatus().intValue());
    }
}

