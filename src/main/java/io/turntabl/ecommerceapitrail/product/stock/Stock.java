package io.turntabl.ecommerceapitrail.product.stock;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Stock {
    @Id
    @SequenceGenerator(
            name = "stock_sequence",
            sequenceName = "stock_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_sequence"
    )
    private Long Id;
    private Long product;
    private Integer quantity = 0;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Stock() {
    }

    public Stock(Long Id, Long productID, Integer quantity) {
        this.product = productID;
        this.quantity = quantity;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Stock(Long productID, Integer quantity) {
        this.product = productID;
        this.quantity = quantity;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }


    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        return "Stock{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}