package io.turntabl.ecommerceapitrail.product;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Product() {
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Product(String name) {
        this.name = name;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}
