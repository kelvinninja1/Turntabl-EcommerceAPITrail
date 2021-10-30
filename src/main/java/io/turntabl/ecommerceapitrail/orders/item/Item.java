package io.turntabl.ecommerceapitrail.orders.item;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long Id;
    private Long orderID;
    private Integer quantity = 0;
    private Long product;
    private Long price;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Item() {
    }

    public Item(Long Id, Long orderID, Integer quantity, Long product, Long price) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Item(Long orderID, Integer quantity, Long product, Long price) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
        return "Item{" +
                "Id=" + Id +
                ", orderID=" + orderID +
                ", quantity=" + quantity +
                ", product=" + product +
                ", price=" + price +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}