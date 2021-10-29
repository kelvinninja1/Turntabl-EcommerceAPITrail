package io.turntabl.ecommerceapitrail.product.price;

import java.math.BigDecimal;
import java.time.LocalDate;
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

@ContextConfiguration(classes = {PriceService.class})
@ExtendWith(SpringExtension.class)
class PriceServiceTest {

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPrices() {
    }

    @Test
    void addPrices() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void findPrice() {
        Long productID = 1L;
        Price price = new Price(productID, BigDecimal.valueOf(42));
        when(priceRepository.findFirstByProduct((Long) any())).thenReturn(price);

        Price actualFindPriceResult = priceService.findPrice(productID);
        assertSame(price, actualFindPriceResult);
        assertEquals(price.getAmount().toString(), actualFindPriceResult.getAmount().toString());
        verify(priceRepository).findFirstByProduct((Long) any());
        assertTrue(priceService.getPrices().isEmpty());
    }

    @Test
    void deletePrice() {
    }

    @Test
    void updatePrice() {
    }

    @Test
    void canGetPriceAmountsByProductsIDs() {
        List<Long> productIDs = List.of(2L, 3L, 5L);
        List<BigDecimal> resultAmounts = List.of(
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(100)
        );
        when(priceRepository.findAllAmountsByProductsIDs((List<Long>) any())).thenReturn(resultAmounts);

        List<BigDecimal> actualPriceAmountsByProductsIDs = priceService
                .getPriceAmountsByProductsIDs(productIDs);
        assertSame(resultAmounts, actualPriceAmountsByProductsIDs);
        assertEquals(resultAmounts, actualPriceAmountsByProductsIDs);
        assertFalse(actualPriceAmountsByProductsIDs.isEmpty());

        verify(priceRepository).findAllAmountsByProductsIDs((List<Long>) any());
        assertTrue(priceService.getPrices().isEmpty());
    }


}