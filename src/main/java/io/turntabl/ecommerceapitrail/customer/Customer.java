package io.turntabl.ecommerceapitrail.customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Customer() {
    }

    public Customer(Long id, String name, LocalDate dateAdded, LocalDate dateModified) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    public Customer(String name, LocalDate dateAdded, LocalDate dateModified) {
        this.name = name;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}
