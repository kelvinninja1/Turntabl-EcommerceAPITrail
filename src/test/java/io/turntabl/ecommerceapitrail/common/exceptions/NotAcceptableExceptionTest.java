package io.turntabl.ecommerceapitrail.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class NotAcceptableExceptionTest {

    String exceptionMessage = "An error occurred";
    String exceptionClassPath = "io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException";
    String throwableClassPath = "java.lang.Throwable";

    @Test
    void canConstructNotAcceptableExceptionWithMessage() {
        NotAcceptableException actualNotAcceptableException = new NotAcceptableException(exceptionMessage);
        assertNull(actualNotAcceptableException.getCause());
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualNotAcceptableException.toString());
        assertEquals(exceptionMessage, actualNotAcceptableException.getMessage());
    }

    @Test
    void canConstructNotAcceptableExceptionWithMessageAndThrowable() {
        Throwable throwable = new Throwable();
        NotAcceptableException actualNotAcceptableException = new NotAcceptableException(exceptionMessage, throwable);

        Throwable cause = actualNotAcceptableException.getCause();
        assertSame(throwable, cause);
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualNotAcceptableException.toString());
        assertEquals(exceptionMessage, actualNotAcceptableException.getMessage());
        assertNull(cause.getCause());
        assertEquals(throwableClassPath, cause.toString());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }
}

