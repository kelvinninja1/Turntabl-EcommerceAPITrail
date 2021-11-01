package io.turntabl.ecommerceapitrail.orders.item;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.LongSummaryStatistics;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    void tearDown() {
        itemRepository.deleteAll();
    }

    @Test
    void findAllByQuantityGreaterThan() {
    }

    @Test
    void findAllByQuantityLessThan() {
    }

    @Test
    void findByOrder() {
    }

    @Test
    void existsByOrder() {
    }

    @Test
    void findByProduct() {
    }

    @Test
    void existsByProduct() {
    }

    @Test
    void deleteByOrder() {
    }

    @Test
    void deleteAllByOrder() {
    }

    @Test
    void findAllByOrder() {
    }

    @Test
    void canFindAllIDsByProduct() {
        Long productID = 2L;
        List<Item> items = List.of(
                new Item(1L, 1, 1L, 1L),
                new Item(1L, 1, productID, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(1L, 1, 3L, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(1L, 1, productID, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(1L, 1, 5L, 1L),
                new Item(1L, 1, 4L, 1L)
        );

        itemRepository.saveAll(items);
        List<Long> IDsByProduct = itemRepository.findAllIDsByProduct(productID);
        assertNotNull(IDsByProduct);
        assertFalse(IDsByProduct.isEmpty());
        assertEquals(2, IDsByProduct.size());
 //        assertEquals(2, IDsByProduct.get(0));
//        assertEquals(6, IDsByProduct.get(1));
    }

    @Test
    void canFindAllDistinctProductByOrderIDs() {
        List<Long> orderIDs = List.of(2L, 3L, 6L);
        List<Item> items = List.of(
                new Item(1L, 1, 1L, 1L),
                new Item(3L, 1, 2L, 1L),
                new Item(2L, 1, 1L, 1L),
                new Item(6L, 1, 3L, 1L),
                new Item(6L, 1, 1L, 1L),
                new Item(1L, 1, 2L, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(2L, 1, 5L, 1L),
                new Item(1L, 1, 4L, 1L)
        );
        itemRepository.saveAll(items);
        List<Long> allDistinctProductByOrderIDs = itemRepository.findAllDistinctProductByOrderIDs(orderIDs);
        assertNotNull(allDistinctProductByOrderIDs);
        assertFalse(allDistinctProductByOrderIDs.isEmpty());
        assertEquals(4, allDistinctProductByOrderIDs.size());
        assertEquals(1L, allDistinctProductByOrderIDs.get(0));
        assertEquals(2L, allDistinctProductByOrderIDs.get(1));
        assertEquals(3L, allDistinctProductByOrderIDs.get(2));
        assertEquals(5L, allDistinctProductByOrderIDs.get(3));
    }

    @Test
    void canCountProductByProductIDs() {
        List<Long> productIDs = List.of(2L, 3L, 6L);
        List<Item> items = List.of(
                new Item(1L, 1, 1L, 1L),
                new Item(3L, 1, 2L, 1L),
                new Item(2L, 1, 1L, 1L),
                new Item(6L, 1, 3L, 1L),
                new Item(6L, 1, 1L, 1L),
                new Item(1L, 1, 2L, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(2L, 1, 5L, 1L),
                new Item(1L, 1, 6L, 1L)
        );
        itemRepository.saveAll(items);
        List<Integer> countProductByProductIDs = itemRepository.CountProductByProductIDs(productIDs);
        assertNotNull(countProductByProductIDs);
        assertFalse(countProductByProductIDs.isEmpty());
        assertEquals(3, countProductByProductIDs.size());
        assertEquals(2, countProductByProductIDs.get(0));
        assertEquals(1, countProductByProductIDs.get(1));
        assertEquals(1, countProductByProductIDs.get(2));
    }

    @Test
    void canCountAllByProduct() {
        Long productID = 2L;
        List<Item> items = List.of(
                new Item(1L, 1, 1L, 1L),
                new Item(3L, 1, 2L, 1L),
                new Item(2L, 1, 1L, 1L),
                new Item(6L, 1, 3L, 1L),
                new Item(6L, 1, 1L, 1L),
                new Item(1L, 1, 2L, 1L),
                new Item(1L, 1, 1L, 1L),
                new Item(2L, 1, 5L, 1L),
                new Item(1L, 1, 6L, 1L)
        );

        itemRepository.saveAll(items);
        Integer countAllByProduct = itemRepository.countAllByProduct(productID);

        assertNotNull(countAllByProduct);
        assertEquals(2, countAllByProduct);
    }

}