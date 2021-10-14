package io.turntabl.ecommerceapitrail.common.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApiExceptionTest {

    String exceptionMessage = "An error occurred";

    @Test
    void canCreateApiExceptionWithConstructor() {
        ApiException actualApiException = new ApiException(exceptionMessage, HttpStatus.I_AM_A_TEAPOT, HttpStatus.I_AM_A_TEAPOT.value(), ZonedDateTime.now(ZoneId.of("Z")));

        assertEquals(HttpStatus.I_AM_A_TEAPOT, actualApiException.getHttpStatus());
        assertEquals(exceptionMessage, actualApiException.getMessage());
        assertEquals(HttpStatus.I_AM_A_TEAPOT.value(), actualApiException.getStatus().intValue());
        assertNotNull(actualApiException.getTimestamp());
    }
}

