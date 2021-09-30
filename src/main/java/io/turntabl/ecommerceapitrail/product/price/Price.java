package io.turntabl.ecommerceapitrail.product.price;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Price {
    @Id
    @SequenceGenerator(
            name = "price_sequence",
            sequenceName = "price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "price_sequence"
    )
    private Long Id;
    private Long product;
    private BigDecimal amount = BigDecimal.valueOf(0);
    private LocalDate dateAdded;
    private LocalDate dateModified;

    public Price() {
    }

    public Price(Long Id, Long productID, BigDecimal amount) {
        this.product = productID;
        this.amount = amount;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Price(Long productID, BigDecimal amount) {
        this.product = productID;
        this.amount = amount;
        this.dateAdded = LocalDate.now();
        this.dateModified = LocalDate.now();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
        return "Price{" +
                "product=" + product +
                ", amount=" + amount +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                '}';
    }
}