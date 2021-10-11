package io.turntabl.ecommerceapitrail.product.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public List<Price> getPrices() {
        return priceRepository.findAll();
    }

    public Price addPrices(Price price) {
        if (price == null) {
            throw new IllegalStateException("Price details are empty");
        }
        priceRepository.save(price);
        return price;
    }

    public Price getPrice(Long productID) {
        return priceRepository.findByProduct(productID).orElseThrow(() -> new IllegalStateException("Price with Product ID:" + productID + " does not exist"));
    }

    public void deletePrice(Long productID) {
        boolean exists = priceRepository.existsByProduct(productID);
        if (!exists) {
            throw new IllegalStateException("Price with Product ID:" + productID + " does not exist");
        }
        priceRepository.deleteByProduct(productID);
    }

    @Transactional
    public Price updatePrice(Long productID, Price newPrice) {
        BigDecimal amount = newPrice.getAmount();
        Price price = priceRepository.findByProduct(productID).orElseThrow(() -> new IllegalStateException("Price with Product ID:" + productID + " does not exist"));

        if (!Objects.equals(amount, price.getAmount())) {
            price.setAmount(amount);
            price.setDateModified(LocalDate.now());
        }
        return price;
    }

}
