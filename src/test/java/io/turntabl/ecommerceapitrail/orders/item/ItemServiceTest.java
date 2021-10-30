package io.turntabl.ecommerceapitrail.orders.item;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ItemService.class})
@ExtendWith(SpringExtension.class)
class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void getItems() {
    }

    @Test
    void getOrderItems() {
    }

    @Test
    void addItems() {
    }

    @Test
    void getItem() {
    }

    @Test
    void deleteItems() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void canGetOrderIDsByProduct() {
        List<Long> resultOrderIDs = List.of(2L, 6L);
        when(itemRepository.findAllIDsByProduct((Long) any())).thenReturn(resultOrderIDs);

        List<Long> actualOrderIDsByProduct = itemService.getOrderIDsByProduct(1L);
        assertSame(resultOrderIDs, actualOrderIDsByProduct);
        assertEquals(resultOrderIDs, actualOrderIDsByProduct);
        assertFalse(actualOrderIDsByProduct.isEmpty());

        verify(itemRepository).findAllIDsByProduct((Long) any());
        assertTrue(itemService.getItems().isEmpty());
    }

    @Test
    void canGetProductIDsByOrderIDs() {
        List<Long> productIDs = List.of(3L, 7L, 4L, 6L);
        List<Long> orderIDs = List.of(2L, 6L);
        when(itemRepository.findAllDistinctProductByOrderIDs((List<Long>) any())).thenReturn(productIDs);

        List<Long> actualProductIDsByOrderIDs = itemService.getProductIDsByOrderIDs(orderIDs);
        assertSame(productIDs, actualProductIDsByOrderIDs);
        assertEquals(productIDs, actualProductIDsByOrderIDs);
        assertFalse(actualProductIDsByOrderIDs.isEmpty());

        verify(itemRepository).findAllDistinctProductByOrderIDs((List<Long>) any());
        assertTrue(itemService.getItems().isEmpty());
    }

    @Test
    void getProductCountsByProductIDs() {
    }

    @Test
    void canGetCountByProduct() {
        Long productID = 2L;
        Integer countAllByProduct = 3;

        when(itemRepository.countAllByProduct((Long) any())).thenReturn(countAllByProduct);
        assertEquals(3, itemService.getCountByProduct(productID));

        verify(itemRepository).countAllByProduct((Long) any());
        assertTrue(itemService.getItems().isEmpty());
    }

}