package io.turntabl.ecommerceapitrail.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProducts(Product product) {
        if (product == null) {
            throw new IllegalStateException("Product details are empty");
        }
        productRepository.save(product);

        return product;
    }

    public Product getProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new IllegalStateException("Product with ID:" + productID + " does not exist"));
        return product;
    }

    public void deleteProduct(Long productID) {
        boolean exists = productRepository.existsById(productID);
        if (!exists) {
            throw new IllegalStateException("Product with ID:" + productID + " does not exist");
        }
        productRepository.deleteById(productID);
    }

    @Transactional
    public void updateProduct(Long productID, Map<String, Object> change) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new IllegalStateException("Product with ID:" + productID + " does not exist"));

        String name = change.get("name").toString();
        if (name != null && name.length() > 0 && !Objects.equals(name, product.getName())) {
            product.setName(name);
            product.setDateModified(LocalDate.now());
        }
    }
}
