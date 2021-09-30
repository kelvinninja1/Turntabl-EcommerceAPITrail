package io.turntabl.ecommerceapitrail.product;

import io.turntabl.ecommerceapitrail.product.stock.Stock;
import io.turntabl.ecommerceapitrail.product.stock.StockService;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Transient
    private BigDecimal price = BigDecimal.valueOf(0);

    @Transient
    private Integer quantity = 0;

    public Product() {
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Product(Long id, String name, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Product(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
