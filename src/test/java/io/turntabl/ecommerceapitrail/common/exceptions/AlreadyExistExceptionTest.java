package io.turntabl.ecommerceapitrail.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlreadyExistExceptionTest {

    String exceptionMessage = "An error occurred";
    String exceptionClassPath = "io.turntabl.ecommerceapitrail.common.exceptions.AlreadyExistException";
    String throwableClassPath = "java.lang.Throwable";

    @Test
    void canConstructAlreadyExistExceptionWithMessage() {

        AlreadyExistException actualAlreadyExistException = new AlreadyExistException(exceptionMessage);
        assertNull(actualAlreadyExistException.getCause());
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualAlreadyExistException.toString());
        assertEquals(exceptionMessage, actualAlreadyExistException.getMessage());
    }

    @Test
    void canConstructAlreadyExistExceptionWithMessageAndThrowable() {
        Throwable throwable = new Throwable();
        AlreadyExistException actualAlreadyExistException = new AlreadyExistException(exceptionMessage, throwable);

        Throwable cause = actualAlreadyExistException.getCause();
        assertSame(throwable, cause);
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualAlreadyExistException.toString());
        assertEquals(exceptionMessage, actualAlreadyExistException.getMessage());
        assertNull(cause.getCause());
        assertEquals(throwableClassPath, cause.toString());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }
}