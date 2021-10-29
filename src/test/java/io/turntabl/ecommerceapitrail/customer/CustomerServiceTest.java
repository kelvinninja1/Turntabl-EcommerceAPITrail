package io.turntabl.ecommerceapitrail.customer;

import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void canGetCustomers() {
        List<Customer> resultCustomers = List.of(
                new Customer("Maya Holiday"),
                new Customer("Nathan Banks"),
                new Customer("Andrew Garfield")
        );
        when(customerRepository.findAll()).thenReturn(resultCustomers);
        ResponseEntity<List<Customer>> actualCustomers = customerService.getCustomers();
        assertEquals("<200 OK OK," + resultCustomers + ",[]>", actualCustomers.toString());
        assertTrue(actualCustomers.hasBody());
        assertEquals(HttpStatus.OK, actualCustomers.getStatusCode());
        assertTrue(actualCustomers.getHeaders().isEmpty());
        verify(customerRepository).findAll();
    }

    @Test
    void canAddCustomers() {
        Customer customer = new Customer("Maya Holiday");
        customerService.addCustomers(customer);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass((Customer.class));
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertEquals(capturedCustomer, customer);
    }

    @Test
    void getCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void checkIfCustomerExists() {
    }

    @Test
    void canGetCustomersIn() {
        List<Customer> resultCustomers = List.of(
                new Customer("Maya Holiday"),
                new Customer("Nathan Banks"),
                new Customer("Andrew Garfield")
        );
        when(customerRepository.findAllByIdIn((List<Long>) any())).thenReturn(resultCustomers);
        List<Long> customerIDs = List.of(2L, 3L, 6L);
        ResponseEntity<List<Customer>> actualCustomersIn = customerService.getCustomersIn(customerIDs);
        assertEquals(resultCustomers, actualCustomersIn.getBody());
        assertEquals("<200 OK OK," + resultCustomers + ",[]>", actualCustomersIn.toString());
        assertEquals(HttpStatus.OK, actualCustomersIn.getStatusCode());
        assertTrue(actualCustomersIn.getHeaders().isEmpty());
        verify(customerRepository).findAllByIdIn((List<Long>) any());
        assertTrue(customerService.getCustomers().getBody().isEmpty());
    }

    @Test
    void canCalculateSpend() {
        List<Long> productIDsByOrderIDs = List.of(2L, 3L, 5L);
        List<Integer> productCountsByProductIDs = List.of(3, 15, 12);
        List<BigDecimal> allAmountsByProductsIDs = List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(5), BigDecimal.valueOf(100));
        BigDecimal actualCalculateSpendResult = customerService.calculateSpend(
                productIDsByOrderIDs,
                productCountsByProductIDs,
                allAmountsByProductsIDs
        );
        assertNotNull(actualCalculateSpendResult);
        assertEquals(BigDecimal.valueOf(1305), actualCalculateSpendResult);
    }
}