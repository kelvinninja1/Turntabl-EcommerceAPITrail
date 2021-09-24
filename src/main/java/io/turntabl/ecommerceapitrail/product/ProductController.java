package io.turntabl.ecommerceapitrail.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> listProducts(){
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addProducts(@RequestBody Product product){
        productService.addProducts(product);
        return List.of("Success", product);
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
}
