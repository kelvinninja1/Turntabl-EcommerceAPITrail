package io.turntabl.ecommerceapitrail.product.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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


    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public Stock addStocks(Stock stock) {
        if (stock == null) {
            throw new IllegalStateException("Stock details are empty");
        }
        stockRepository.save(stock);
        return stock;
    }

    public Stock getStock(Long productID) {
        return stockRepository.findByProduct(productID).orElseThrow(() -> new IllegalStateException("Stock with Product ID:" + productID + " does not exist"));
    }

    public void deleteStock(Long productID) {
        boolean exists = stockRepository.existsByProduct(productID);
        if (!exists) {
            throw new IllegalStateException("Stock with Product ID:" + productID + " does not exist");
        }
        stockRepository.deleteByProduct(productID);
    }

    @Transactional
    public void updateStock(Long productID, Integer quantity) {
        Stock stock = stockRepository.findByProduct(productID).orElseThrow(() -> new IllegalStateException("Stock with Product ID:" + productID + " does not exist"));

        if (quantity > 0 && !Objects.equals(quantity, stock.getQuantity())) {
            stock.setQuantity(quantity);
            stock.setDateModified(LocalDate.now());
        }
    }

    public List<Stock> getAvailableStocks() {
        return stockRepository.findAllByQuantityGreaterThan(0);
    }

    public List<Stock> getUnavailableStocks() {
        return stockRepository.findAllByQuantityLessThan(1);
    }
}
