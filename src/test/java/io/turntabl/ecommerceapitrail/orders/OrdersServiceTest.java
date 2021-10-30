package io.turntabl.ecommerceapitrail.orders;

import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrdersService.class})
@ExtendWith(SpringExtension.class)
class OrdersServiceTest {

    @MockBean
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @AfterEach
    void tearDown() {

    }

    @Test
    void getOrders() {
    }

    @Test
    void addOrders() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void getOrdersOverLastXMonths() {
    }

    @Test
    void getCustomersWithMultipleOrders() {
    }

    @Test
    void canGetCustomerIDsByOrderIds() {
        List<Long> resultCustomerIDs = List.of(2L, 3L, 6L);
        List<Long> orderIds = List.of(2L, 3L, 6L);
        when(this.ordersRepository.findAllCustomerIDsByOrderIds((List<Long>) any()))
                .thenReturn(resultCustomerIDs);

        List<Long> actualCustomerIDsByOrderIds = this.ordersService.getCustomerIDsByOrderIds(orderIds);
        assertSame(resultCustomerIDs, actualCustomerIDsByOrderIds);
        assertEquals(resultCustomerIDs, actualCustomerIDsByOrderIds);
        assertFalse(actualCustomerIDsByOrderIds.isEmpty());

        verify(this.ordersRepository).findAllCustomerIDsByOrderIds((List<Long>) any());
        assertTrue(this.ordersService.getOrders().getBody().isEmpty());
    }

    @Test
    void canGetOrderIDsCustomer() {
        List<Long> orderIds = List.of(2L, 3L, 6L);
        when(this.ordersRepository.findAllOrderIDsByCustomer((Long) any())).thenReturn(orderIds);

        List<Long> actualOrderIDsCustomer = this.ordersService.getOrderIDsCustomer(1L);
        assertSame(orderIds, actualOrderIDsCustomer);
        assertEquals(orderIds, actualOrderIDsCustomer);
        assertFalse(actualOrderIDsCustomer.isEmpty());

        verify(this.ordersRepository).findAllOrderIDsByCustomer((Long) any());
        assertTrue(this.ordersService.getOrders().getBody().isEmpty());
    }
}