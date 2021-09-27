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
    private Long id;
    private Long product_id;
    private Integer quantity;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Stock() {
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Stock(Long id, Long product_id, Integer quantity) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Stock(Long product_id, Integer quantity) {
        this.product_id = product_id;
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

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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
                "id=" + id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}
