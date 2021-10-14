package io.turntabl.ecommerceapitrail.common.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class BadRequestExceptionTest {

    String exceptionMessage = "An error occurred";
    String exceptionClassPath = "io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException";
    String throwableClassPath = "java.lang.Throwable";

    @Test
    void canConstructBadRequestExceptionWithMessage() {
        BadRequestException actualBadRequestException = new BadRequestException(exceptionMessage);
        assertNull(actualBadRequestException.getCause());
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualBadRequestException.toString());
        assertEquals(exceptionMessage, actualBadRequestException.getMessage());
    }

    @Test
    void canConstructBadRequestExceptionWithMessageAndThrowable() {
        Throwable throwable = new Throwable();
        BadRequestException actualBadRequestException = new BadRequestException(exceptionMessage, throwable);

        Throwable cause = actualBadRequestException.getCause();
        assertSame(throwable, cause);
        assertEquals(exceptionClassPath + ": " + exceptionMessage,
                actualBadRequestException.toString());
        assertEquals(exceptionMessage, actualBadRequestException.getMessage());
        assertNull(cause.getCause());
        assertEquals(throwableClassPath, cause.toString());
        assertNull(cause.getMessage());
        assertSame(cause, throwable);
    }
}

