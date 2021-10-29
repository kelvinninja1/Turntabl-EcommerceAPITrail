package io.turntabl.ecommerceapitrail.product.price;

import io.turntabl.ecommerceapitrail.common.exceptions.AlreadyExistException;
import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Price> addPrices(Price price) {
        if (price.getAmount() != null && price.getProduct() != null && price.getProduct() > 0) {
            boolean exists = priceRepository.existsByProduct(price.getProduct());
            if (exists) {
                throw new AlreadyExistException("Price with Product ID:" + price.getProduct() + " already exist");
            }
            Price newPrice = priceRepository.save(price);
            return new ResponseEntity<Price>(newPrice, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Price details are empty");
        }
    }

    public ResponseEntity<Price> getPrice(Long productID) {
        return new ResponseEntity<Price>(findPrice(productID), HttpStatus.OK);
    }

    public Price findPrice(Long productID) {
        Price price = priceRepository.findFirstByProduct(productID);
        return price;
    }

    public ResponseEntity<Price> deletePrice(Long productID) {
        boolean exists = priceRepository.existsByProduct(productID);
        if (!exists) {
            throw new NotFoundException("Price with Product ID:" + productID + " does not exist");
        }
        priceRepository.deleteByProduct(productID);
        return new ResponseEntity<Price> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Price> updatePrice(Long productID, Price updatedPrice) {
        BigDecimal amount = updatedPrice.getAmount();
        Price price = priceRepository.findFirstByProduct(productID);

        if (amount != null && amount.equals(BigDecimal.valueOf(0))) {
            if (Objects.equals(amount, price.getAmount())) {
                throw new NotAcceptableException("No change Required, Updated details already exist");
            } else {
                BeanUtils.copyProperties(updatedPrice, price);
                price.setProduct(productID);
                price.setDateModified(LocalDate.now());
                priceRepository.save(price);
                return new ResponseEntity<Price>(price, HttpStatus.ACCEPTED);
            }
        }
        else {
            throw new BadRequestException("Price details are empty, bad or Un-formatted");
        }
    }

    public List<BigDecimal> getPriceAmountsByProductsIDs(List<Long> productIDsByOrderIDs) {
        return priceRepository.findAllAmountsByProductsIDs(productIDsByOrderIDs);
    }

}
