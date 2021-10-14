package io.turntabl.ecommerceapitrail.product;

import com.sun.istack.NotNull;
import io.turntabl.ecommerceapitrail.product.price.Price;
import io.turntabl.ecommerceapitrail.product.price.PriceService;
import io.turntabl.ecommerceapitrail.product.stock.Stock;
import io.turntabl.ecommerceapitrail.product.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {
    private final ProductService productService;
    private final StockService stockService;
    private final PriceService priceService;

    @Autowired
    public ProductController(ProductService productService, StockService stockService, PriceService priceService) {
        this.productService = productService;
        this.stockService = stockService;
        this.priceService = priceService;
    }



    @GetMapping
    public ResponseEntity<List<Product>> listProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProducts(@NotNull @RequestBody Product product){
        return productService.addProducts(product);
    }

    @GetMapping(path = "{productID}")
    public ResponseEntity<Product> getProduct(@NotNull @PathVariable("productID") Long productID){
        return productService.getProduct(productID);
    }

    @DeleteMapping(path = "{productID}")
    public ResponseEntity<Product> deleteProduct(@NotNull @PathVariable("productID") Long productID){
        return productService.deleteProduct(productID);
    }

    @PutMapping(path = "{productID}")
    public ResponseEntity<Product> updateProduct(@NotNull @PathVariable("productID") Long productID, @NotNull  @RequestBody Product updatedProduct){
        return productService.updateProduct(productID, updatedProduct);
    }

    @GetMapping(path = "/stock")
    public ResponseEntity<List<Stock>> listProductsStock(){
        return stockService.getStocks();
    }

    @PostMapping(path = "/stock")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Stock> addStocks(@NotNull @RequestBody Stock stock){
        productService.checkIfProductExists(stock.getProduct());
        return stockService.addStocks(stock);
    }

    @GetMapping(path = "/stock/available")
    public ResponseEntity<List<Stock>> listProductsStockAvailable(){
        return stockService.getAvailableStocks();
    }

    @GetMapping(path = "/stock/unavailable")
    public ResponseEntity<List<Stock>> listProductsStockUnavailable(){
        return stockService.getUnavailableStocks();
    }


    @GetMapping(path = "{productID}/stock")
    public ResponseEntity<Stock> getProductStock(@NotNull @PathVariable("productID") Long productID){
        return stockService.getStock(productID);
    }

    @PutMapping(path = "{productID}/stock")
    public ResponseEntity<Stock> updateStock(@NotNull @PathVariable("productID") Long productID, @NotNull @RequestBody Stock updatedStock){
        return stockService.updateStock(productID, updatedStock);
    }

    @DeleteMapping(path = "{productID}/stock")
    public ResponseEntity<Stock> deleteProductStock(@NotNull @PathVariable("productID") Long productID){
        return stockService.deleteStock(productID);
    }

    @PostMapping(path = "/price")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Price> addPrices(@NotNull @RequestBody Price price){
        productService.checkIfProductExists(price.getProduct());
        return priceService.addPrices(price);
    }

    @PutMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> updatePrice(@NotNull @PathVariable("productID") Long productID, @NotNull @RequestBody Price price){
        return priceService.updatePrice(productID, price);
    }

    @GetMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> getProductPrice(@NotNull @PathVariable("productID") Long productID){
        return priceService.getPrice(productID);
    }

    @DeleteMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Price> deleteProductPrice(@NotNull @PathVariable("productID") Long productID){
        return priceService.deletePrice(productID);
    }

}