package io.turntabl.ecommerceapitrail.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    Long Id = 1L;
    String name = "M1 MacBook Pro";
    LocalDate dateAdded = LocalDate.now();
    LocalDate dateModified = LocalDate.now();
    BigDecimal price = BigDecimal.valueOf(10);
    Integer quantity = 5;


    @Test
    void testEmptyConstructor() {

        Product actualProduct = new Product();
        actualProduct.setId(Id);
        actualProduct.setName(name);
        actualProduct.setDateAdded(dateAdded);
        actualProduct.setDateModified(dateModified);

        assertEquals(dateAdded, actualProduct.getDateAdded());
        assertEquals(dateModified, actualProduct.getDateModified());
        assertEquals(Id, actualProduct.getId().longValue());
        assertEquals(name, actualProduct.getName());
        assertEquals("Product{id="+Id+", name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualProduct.toString());
    }

    @Test
    void testConstructorWithID() {
        Product actualProduct = new Product(Id, name, price, quantity);

        assertEquals(Id, actualProduct.getId().longValue());
        assertEquals(name, actualProduct.getName());
        assertEquals(dateAdded, actualProduct.getDateAdded());
        assertEquals(dateModified, actualProduct.getDateModified());
        assertEquals("Product{id="+Id+", name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualProduct.toString());
    }

    @Test
    void testConstructorWithoutID() {
        Product actualProduct = new Product(name, price, quantity);

        assertEquals(name, actualProduct.getName());
        assertEquals(dateAdded, actualProduct.getDateAdded());
        assertEquals(dateModified, actualProduct.getDateModified());
        assertEquals("Product{id=null, name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualProduct.toString());
    }

}

