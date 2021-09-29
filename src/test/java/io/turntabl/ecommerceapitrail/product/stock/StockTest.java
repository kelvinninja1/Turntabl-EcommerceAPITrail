package io.turntabl.ecommerceapitrail.product.stock;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Long Id = 1L;
    Long productID = 1L;
    Integer quantity = 1;
    LocalDate dateAdded = LocalDate.now();
    LocalDate dateModified = LocalDate.now();


    @Test
    void testEmptyConstructor() {

        Stock actualStock = new Stock();
        actualStock.setProduct_id(productID);
        actualStock.setQuantity(quantity);
        actualStock.setDateAdded(dateAdded);
        actualStock.setDateModified(dateModified);

        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals(productID, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals("Stock{product=" + productID +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }

    @Test
    void testConstructorWithID() {
        Stock actualStock = new Stock(productID, quantity);


        assertEquals(productID, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals("Stock{product=" + productID +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }

    @Test
    void testConstructorWithoutID() {
        Stock actualStock = new Stock(productID, quantity);

        assertEquals(productID, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals("Stock{product=1, product=" + productID +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }
}