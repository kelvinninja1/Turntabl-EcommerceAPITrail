package io.turntabl.ecommerceapitrail.orders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Orders {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private Long customer;
    private LocalDate dateAdded;
    private LocalDate dateModified;

    @Transient
    private BigDecimal totalPrice = BigDecimal.valueOf(0);

    public Orders() {
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Orders(Long id, Long customer) {
        this.id = id;
        this.customer = customer;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Orders(Long customer) {
        this.customer = customer;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }

}
