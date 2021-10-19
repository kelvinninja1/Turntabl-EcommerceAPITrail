package io.turntabl.ecommerceapitrail.product.stock;

import io.turntabl.ecommerceapitrail.common.exceptions.AlreadyExistException;
import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public ResponseEntity<List<Stock>> getStocks() {
        return new ResponseEntity<List<Stock>>(stockRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Stock> addStocks(Stock stock) {
        if (stock.getQuantity() != null && stock.getProduct() != null && stock.getProduct() > 0 ) {
            Boolean exists = stockRepository.existsByProduct(stock.getProduct());
            if (exists) {
                throw new AlreadyExistException("Stock with Product ID:" + stock.getProduct() + " already exist");
            }

            Stock newStock = stockRepository.save(stock);
            return new ResponseEntity<Stock>(newStock, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Stock details are empty");
        }
    }

    public ResponseEntity<Stock> getStock(Long productID) {
        Stock stock = stockRepository.findByProduct(productID).orElseThrow(() -> new NotFoundException("Stock with Product ID:" + productID + " does not exist"));
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    public ResponseEntity<Stock> deleteStock(Long productID) {
        boolean exists = stockRepository.existsByProduct(productID);
        if (!exists) {
            throw new NotFoundException("Stock with Product ID:" + productID + " does not exist");
        }
        stockRepository.deleteByProduct(productID);
        return new ResponseEntity<Stock> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Stock> updateStock(Long productID, Stock updatedStock) {
        Integer quantity = updatedStock.getQuantity();
        Stock stock = stockRepository.findByProduct(productID).orElseThrow(() -> new NotFoundException("Stock with Product ID:" + productID + " does not exist"));

        if (quantity != null && quantity > 0) {
            if (Objects.equals(quantity, stock.getQuantity())) {
                throw new NotAcceptableException("No change Required, Updated details already exist");
            } else {
                BeanUtils.copyProperties(updatedStock, stock);
                stock.setProduct(productID);
                stock.setDateModified(LocalDate.now());
                stockRepository.save(stock);
                return new ResponseEntity<Stock>(stock, HttpStatus.ACCEPTED);
            }
        }
        else {
            throw new BadRequestException("Stock details are empty, bad or Un-formatted");
        }
    }

    public ResponseEntity<List<Stock>> getAvailableStocks() {
        List<Stock> stocks = stockRepository.findAllByQuantityGreaterThan(0);
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }

    public ResponseEntity<List<Stock>> getUnavailableStocks() {
        List<Stock> stocks = stockRepository.findAllByQuantityLessThan(1);
        return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
    }
}
