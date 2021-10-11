package io.turntabl.ecommerceapitrail.product;

import io.turntabl.ecommerceapitrail.product.price.Price;
import io.turntabl.ecommerceapitrail.product.price.PriceService;
import io.turntabl.ecommerceapitrail.product.stock.Stock;
import io.turntabl.ecommerceapitrail.product.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @ResponseStatus(HttpStatus.OK)
    public List<Product> listProducts(){
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addProducts(@RequestBody Product product){
        Product newProduct = productService.addProducts(product);
        return List.of("Success", newProduct);
    }

    @GetMapping(path = "{productID}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("productID") Long productID){
        return productService.getProduct(productID);
    }

    @DeleteMapping(path = "{productID}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteProduct(@PathVariable("productID") Long productID){
        productService.deleteProduct(productID);
        return List.of("Success");
    }

    @PutMapping(path = "{productID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> updateProduct(@PathVariable("productID") Long productID, @RequestBody Map<String, Object> change){
        productService.updateProduct(productID, change);
        return List.of("Success",
                change);
    }

    @GetMapping(path = "/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> listProductsStock(){
        return stockService.getStocks();
    }

    @PostMapping(path = "/stock")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addStocks(@RequestBody Stock stock){
        Stock newStock = stockService.addStocks(stock);
        return List.of("Success", newStock);
    }

    @GetMapping(path = "/stock/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> listProductsStockAvailable(){
        return stockService.getAvailableStocks();
    }

    @GetMapping(path = "/stock/unavailable")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> listProductsStockUnavailable(){
        return stockService.getUnavailableStocks();
    }


    @GetMapping(path = "{productID}/stock")
    @ResponseStatus(HttpStatus.OK)
    public Stock getProductStock(@PathVariable("productID") Long productID){
        return stockService.getStock(productID);
    }

    @PutMapping(path = "{productID}/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> updateStock(@PathVariable("productID") Long productID, @RequestBody Stock stock){
        Stock newStock = stockService.updateStock(productID, stock);
        return List.of("Success",
                newStock);
    }

    @DeleteMapping(path = "{productID}/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteProductStock(@PathVariable("productID") Long productID){
        stockService.deleteStock(productID);
        return List.of("Success");
    }

    @PostMapping(path = "/price")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addPrices(@RequestBody Price price){
        Price newPrice = priceService.addPrices(price);
        return List.of("Success", newPrice);
    }

    @PutMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> updatePrice(@PathVariable("productID") Long productID, @RequestBody Price price){
        Price newPrice = priceService.updatePrice(productID, price);
        return List.of("Success",
                newPrice);
    }

    @GetMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public Price getProductPrice(@PathVariable("productID") Long productID){
        return priceService.getPrice(productID);
    }

    @DeleteMapping(path = "{productID}/price")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteProductPrice(@PathVariable("productID") Long productID){
        priceService.deletePrice(productID);
        return List.of("Success");
    }

}