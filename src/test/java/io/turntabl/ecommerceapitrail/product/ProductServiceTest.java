package io.turntabl.ecommerceapitrail.product;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void canGetProducts() {
        //when
//        productService.getProducts();
        //then
//        verify(productRepository).findAll();
    }

    @Test
    void canAddProducts() {
//        //given
//        Product product = new Product(
//                "M1 MacBook Pro"
//        );
//        //when
////        productService.addProducts(product);
//        //then
//        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass((Product.class));
//
////        verify(productRepository).save(productArgumentCaptor.capture());
//        Product capturedProduct = productArgumentCaptor.getValue();
//
//        assertEquals(capturedProduct, product);
    }

    @Test
    void getProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void checkIfProductExists() {
    }

    @Test
    void testCalculateTotalSales() {
        assertEquals("126", this.productService.calculateTotalSales(3, BigDecimal.valueOf(42L)).toString());
        assertEquals("42", this.productService.calculateTotalSales(1, BigDecimal.valueOf(42L)).toString());
        assertEquals("-42", this.productService.calculateTotalSales(-1, BigDecimal.valueOf(42L)).toString());
    }

    @Test
    void testCalculateTotalSales2() {
        BigDecimal actualCalculateTotalSalesResult = this.productService.calculateTotalSales(0, BigDecimal.valueOf(42L));
        assertSame(actualCalculateTotalSalesResult.ZERO, actualCalculateTotalSalesResult);
        assertEquals("0", actualCalculateTotalSalesResult.toString());
    }

    @Test
    void canCalculateTotalSales() {
        Integer countByProduct = 3;
        BigDecimal price = BigDecimal.valueOf(35);
        BigDecimal calculateTotalSales = productService.calculateTotalSales(countByProduct, price);
        assertEquals(BigDecimal.valueOf(105), calculateTotalSales);
    }


}