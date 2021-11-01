package io.turntabl.ecommerceapitrail.orders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.turntabl.ecommerceapitrail.orders.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByDateAddedAfter() {
    }

    @Test
    void findAllCustomersWithMultipleOrders() {
    }

    @Test
    void canFindAllCustomerIDsByOrderIds() {
        List<Long> orderIds = List.of(2L, 3L, 6L);
        List<Orders> orders = List.of(
                new Orders(1L),
                new Orders(2L),
                new Orders(3L),
                new Orders(2L),
                new Orders(5L),
                new Orders(4L),
                new Orders(6L),
                new Orders(1L),
                new Orders(2L)
        );
        ordersRepository.saveAll(orders);
        List<Long> allCustomerIDs = ordersRepository.findAllCustomerIDsByOrderIds(orderIds);
        assertNotNull(allCustomerIDs);
        assertEquals(3, allCustomerIDs.size());
        assertEquals(2, allCustomerIDs.get(0));
        assertEquals(3, allCustomerIDs.get(1));
        assertEquals(4, allCustomerIDs.get(2));
    }

    @Test
    void findDistinctByIdIn() {
    }

    @Test
    void canFindAllOrderIDsByCustomer() {
        List<Orders> orders = List.of(
                new Orders(1L),
                new Orders(2L),
                new Orders(3L),
                new Orders(2L),
                new Orders(5L),
                new Orders(4L),
                new Orders(6L),
                new Orders(1L),
                new Orders(2L)
        );
        ordersRepository.saveAll(orders);
        List<Long> allOrderIDsByCustomer = ordersRepository.findAllOrderIDsByCustomer(1L);
        assertNotNull(allOrderIDsByCustomer);
        assertEquals(2, allOrderIDsByCustomer.size());
//        assertEquals(1, allOrderIDsByCustomer.get(0));
//        assertEquals(8, allOrderIDsByCustomer.get(1));
    }
}