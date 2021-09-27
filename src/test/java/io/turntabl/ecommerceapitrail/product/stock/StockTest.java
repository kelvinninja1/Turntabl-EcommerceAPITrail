package io.turntabl.ecommerceapitrail.product.stock;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    Long Id = 1L;
    Long product_id = 1L;
    Integer quantity = 1;
    LocalDate dateAdded = LocalDate.now();
    LocalDate dateModified = LocalDate.now();


    @Test
    void testEmptyConstructor() {

        Stock actualStock = new Stock();
        actualStock.setId(Id);
        actualStock.setProduct_id(product_id);
        actualStock.setQuantity(quantity);
        actualStock.setDateAdded(dateAdded);
        actualStock.setDateModified(dateModified);

        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals(Id, actualStock.getId().longValue());
        assertEquals(product_id, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals("Stock{id="+Id+", product_id=" + product_id +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }

    @Test
    void testConstructorWithID() {
        Stock actualStock = new Stock(Id, product_id, quantity);

        assertEquals(Id, actualStock.getId().longValue());
        assertEquals(product_id, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals("Stock{id="+Id+", product_id=" + product_id +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }

    @Test
    void testConstructorWithoutID() {
        Stock actualStock = new Stock(product_id, quantity);

        assertEquals(product_id, actualStock.getProduct_id());
        assertEquals(quantity, actualStock.getQuantity());
        assertEquals(dateAdded, actualStock.getDateAdded());
        assertEquals(dateModified, actualStock.getDateModified());
        assertEquals("Stock{id=null, product_id=" + product_id +", quantity=" + quantity +", dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualStock.toString());
    }
}