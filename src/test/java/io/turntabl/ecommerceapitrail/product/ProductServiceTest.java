package io.turntabl.ecommerceapitrail.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryUnderTest;
    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService(productRepositoryUnderTest);
    }

    @Test
    void canGetProducts() {
        //when
        productServiceUnderTest.getProducts();
        //then
        verify(productRepositoryUnderTest).findAll();
    }

    @Test
    void canAddProducts() {
        //given
        Product product = new Product(
                "M1 MacBook Pro",
                BigDecimal.valueOf(2),
                2);
        //when
        productServiceUnderTest.addProducts(product);
        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass((Product.class));

        verify(productRepositoryUnderTest).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        assertEquals(capturedProduct, product);
    }
}