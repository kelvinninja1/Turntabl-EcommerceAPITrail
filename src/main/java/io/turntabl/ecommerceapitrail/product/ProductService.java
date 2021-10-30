package io.turntabl.ecommerceapitrail.product;

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
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    public ResponseEntity<Product> addProducts(Product product) {
        if (product.getName() != null && product.getName().length() > 0) {
            Product newProduct = productRepository.save(product);
            return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Product details are empty");
        }
    }

    public ResponseEntity<Product> getProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new NotFoundException("Product with ID:" + productID + " does not exist"));
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    public ResponseEntity<Product> deleteProduct(Long productID) {
        checkIfProductExists(productID);
        productRepository.deleteById(productID);
        return new ResponseEntity<Product> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Product> updateProduct(Long productID, Product updatedProduct) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new NotFoundException("Product with ID:" + productID + " does not exist"));

        String name = updatedProduct.getName();
        if (name != null && name.length() > 0) {
            if (Objects.equals(name, product.getName())) {
                throw new NotAcceptableException("No change Required, Updated details already exist");
            } else {
                BeanUtils.copyProperties(updatedProduct, product);
                product.setId(productID);
                product.setDateModified(LocalDate.now());
                productRepository.save(product);
                return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
            }
        }
        else {
            throw new BadRequestException("Product details are empty, bad or Un-formatted");
        }
    }

    public void checkIfProductExists(Long productID){
        boolean exists = productRepository.existsById(productID);
        if (!exists) {
            throw new NotFoundException("Product with ID:" + productID + " does not exist");
        }
    }

    public BigDecimal calculateTotalSales(Integer countByProduct, BigDecimal price) {
        return BigDecimal.valueOf(countByProduct).multiply(price);
    }
}
