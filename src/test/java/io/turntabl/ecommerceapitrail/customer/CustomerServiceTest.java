package io.turntabl.ecommerceapitrail.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepositoryUnderTest;
    private CustomerService customerServiceUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceUnderTest = new CustomerService(customerRepositoryUnderTest);
    }

    @Test
    void canGetCustomers() {
        //when
        customerServiceUnderTest.getCustomers();
        //then
        verify(customerRepositoryUnderTest).findAll();
    }

    @Test
    void canAddCustomers() {
        //given
        Customer customer = new Customer(
                "Maya Holiday"
        );
        //when
        customerServiceUnderTest.addCustomers(customer);
        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass((Customer.class));

        verify(customerRepositoryUnderTest).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertEquals(capturedCustomer, customer);
    }
}