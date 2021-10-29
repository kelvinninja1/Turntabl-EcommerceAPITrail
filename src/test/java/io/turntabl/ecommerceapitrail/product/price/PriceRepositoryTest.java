package io.turntabl.ecommerceapitrail.product.price;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @AfterEach
    void tearDown() {
        priceRepository.deleteAll();
    }

    @Test
    void canFindFirstByProduct() {
        Long productId = 2L;
        List<Price> prices = List.of(
                new Price(1L, BigDecimal.valueOf(20)),
                new Price(2L, BigDecimal.valueOf(10)),
                new Price(3L, BigDecimal.valueOf(5)),
                new Price(4L, BigDecimal.valueOf(38)),
                new Price(5L, BigDecimal.valueOf(100))
        );
        priceRepository.saveAll(prices);

        Price actualFindFirstByProductResult = priceRepository.findFirstByProduct(productId);
        assertSame(prices.get(1), actualFindFirstByProductResult);
        assertEquals(prices.get(1).getAmount().toString(), actualFindFirstByProductResult.getAmount().toString());
    }

    @Test
    void existsByProduct() {
    }

    @Test
    void deleteByProduct() {
    }

    @Test
    void canFindAllAmountsByProductsIDs() {
        List<Long> productIDs = List.of(2L, 3L, 5L);
        List<Price> prices = List.of(
                new Price(1L, BigDecimal.valueOf(20)),
                new Price(2L, BigDecimal.valueOf(10)),
                new Price(3L, BigDecimal.valueOf(5)),
                new Price(4L, BigDecimal.valueOf(38)),
                new Price(5L, BigDecimal.valueOf(100))
        );
        priceRepository.saveAll(prices);
        List<BigDecimal> allAmountsByProductsIDs = priceRepository.findAllAmountsByProductsIDs(productIDs);
        assertNotNull(allAmountsByProductsIDs);
        assertFalse(allAmountsByProductsIDs.isEmpty());
        assertEquals(3, allAmountsByProductsIDs.size());
        assertEquals(BigDecimal.valueOf(5).setScale(2), allAmountsByProductsIDs.get(1));
        assertEquals(BigDecimal.valueOf(5).setScale(2), allAmountsByProductsIDs.get(1));
        assertEquals(BigDecimal.valueOf(100).setScale(2), allAmountsByProductsIDs.get(2));
    }

}