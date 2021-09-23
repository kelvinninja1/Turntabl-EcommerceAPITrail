package io.turntabl.ecommerceapitrail.customer;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    Long Id = 1L;
    String name = "Kelvin Morrison";
    LocalDate dateAdded = LocalDate.now();
    LocalDate dateModified = LocalDate.now();


    @Test
    void testEmptyConstructorWithGettersAndSetters() {

        Customer actualCustomer = new Customer();
        actualCustomer.setId(Id);
        actualCustomer.setName("Kelvin Morrison");
        actualCustomer.setDateAdded(dateAdded);
        actualCustomer.setDateModified(dateModified);

        assertEquals(dateAdded, actualCustomer.getDateAdded());
        assertEquals(dateModified, actualCustomer.getDateModified());
        assertEquals(Id, actualCustomer.getId().longValue());
        assertEquals(name, actualCustomer.getName());
        assertEquals("Customer{id="+Id+", name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualCustomer.toString());
    }

    @Test
    void testConstructorWithID() {
        Customer actualCustomer = new Customer(Id, name);
        assertEquals("Customer{id="+Id+", name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualCustomer.toString());
    }

    @Test
    void testConstructorWithoutID() {
        Customer actualCustomer = new Customer(name);
        assertEquals("Customer{id=null, name='"+name+"', dateAdded="+dateAdded+", dateModified="+dateModified+"}",
                actualCustomer.toString());
    }

}

