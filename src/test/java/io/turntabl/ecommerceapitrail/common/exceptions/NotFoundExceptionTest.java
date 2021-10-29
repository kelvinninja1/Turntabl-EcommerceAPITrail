package io.turntabl.ecommerceapitrail.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {

    String exceptionMessage = "An error occurred";
    String exceptionClassPath = "io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException";
    String throwableClassPath = "java.lang.Throwable";

    @Test
    void canConstructNotFoundExceptionWithMessage() {
        NotFoundException actualNotFoundException = new NotFoundException(exceptionMessage);
        assertNull(actualNotFoundException.getCause());
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualNotFoundException.toString());
        assertEquals(exceptionMessage, actualNotFoundException.getMessage());
    }

    @Test
    void canConstructNotFoundExceptionWithMessageAndThrowable() {
        Throwable throwable = new Throwable();
        NotFoundException actualNotFoundException = new NotFoundException(exceptionMessage, throwable);

        Throwable cause = actualNotFoundException.getCause();
        assertSame(throwable, cause);
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualNotFoundException.toString());
        assertEquals(exceptionMessage, actualNotFoundException.getMessage());
        assertNull(cause.getCause());
        assertEquals(throwableClassPath, cause.toString());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }
}

