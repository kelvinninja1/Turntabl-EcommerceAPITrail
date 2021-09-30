package io.turntabl.ecommerceapitrail.product.price;

import io.turntabl.ecommerceapitrail.product.price.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTest {

    Long Id = 1L;
    Long productID = 1L;
    BigDecimal amount = BigDecimal.valueOf(1);
    LocalDate dateAdded = LocalDate.now();
    LocalDate dateModified = LocalDate.now();


    @Test
    void testEmptyConstructor() {

        Price actualPrice = new Price();
        actualPrice.setProduct(productID);
        actualPrice.setAmount(amount);
        actualPrice.setDateAdded(dateAdded);
        actualPrice.setDateModified(dateModified);

        assertEquals(dateAdded, actualPrice.getDateAdded());
        assertEquals(dateModified, actualPrice.getDateModified());
        assertEquals(productID, actualPrice.getProduct());
        assertEquals(amount, actualPrice.getAmount());
        assertEquals("Price{product=" + productID +", amount=" + amount +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualPrice.toString());
    }

    @Test
    void testConstructorWithID() {
        Price actualPrice = new Price(productID, amount);


        assertEquals(productID, actualPrice.getProduct());
        assertEquals(amount, actualPrice.getAmount());
        assertEquals(dateAdded, actualPrice.getDateAdded());
        assertEquals(dateModified, actualPrice.getDateModified());
        assertEquals("Price{product=" + productID +", amount=" + amount +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualPrice.toString());
    }

    @Test
    void testConstructorWithoutID() {
        Price actualPrice = new Price(productID, amount);

        assertEquals(productID, actualPrice.getProduct());
        assertEquals(amount, actualPrice.getAmount());
        assertEquals(dateAdded, actualPrice.getDateAdded());
        assertEquals(dateModified, actualPrice.getDateModified());
        assertEquals("Price{product=" + productID +", amount=" + amount +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualPrice.toString());
    }
}