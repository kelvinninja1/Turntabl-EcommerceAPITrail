package io.turntabl.ecommerceapitrail.orders;

import io.turntabl.ecommerceapitrail.customer.Customer;
import io.turntabl.ecommerceapitrail.customer.CustomerRepository;
import io.turntabl.ecommerceapitrail.customer.CustomerService;
import io.turntabl.ecommerceapitrail.orders.item.Item;
import io.turntabl.ecommerceapitrail.orders.item.ItemRepository;
import io.turntabl.ecommerceapitrail.orders.item.ItemService;
import io.turntabl.ecommerceapitrail.product.ProductRepository;
import io.turntabl.ecommerceapitrail.product.ProductService;
import io.turntabl.ecommerceapitrail.product.price.Price;
import io.turntabl.ecommerceapitrail.product.price.PriceRepository;
import io.turntabl.ecommerceapitrail.product.price.PriceService;
import io.turntabl.ecommerceapitrail.product.stock.StockRepository;
import io.turntabl.ecommerceapitrail.product.stock.StockService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrdersController.class})
@ExtendWith(SpringExtension.class)
class OrdersControllerTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ItemService itemService;

    @Autowired
    private OrdersController ordersController;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private PriceService priceService;

    @MockBean
    private ProductService productService;

    @MockBean
    private StockService stockService;

    @AfterEach
    void tearDown() {
    }

    @Test
    void listOrders() {
    }

    @Test
    void addOrders() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void deleteOrderItems() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void listOrdersOverLastXMonths() {
    }

    @Test
    void listCustomersWithMultipleOrders() {
    }

    @Test
    void canListCustomersWhoPurchasedProductX() throws Exception {
        Long productID = 1L;
        List<Long> orderIds = List.of(2L, 3L, 6L);
        List<Long> customerIds = List.of(2L, 3L, 4L);
        List<Customer> resultCustomers = List.of(
                new Customer("Maya Holiday"),
                new Customer("Nathan Banks"),
                new Customer("Andrew Garfield")
        );
        when(itemService.getOrderIDsByProduct((Long) any())).thenReturn(orderIds);
        when(ordersService.getCustomerIDsByOrderIds((List<Long>) any())).thenReturn(customerIds);
        when(customerService.getCustomersIn((List<Long>) any()))
                .thenReturn(new ResponseEntity<List<Customer>>(resultCustomers, HttpStatus.OK));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/orders/orders/products/{productID}/customers/", productID);

        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }

    @Test
    void canGetTotalSpendOfCustomer() throws Exception {
        Long customerID = 1L;
        List<Long> orderIDsCustomer = List.of(3L, 6L);
        List<Long> productIDsByOrderIDs = List.of(2L, 3L, 5L);
        List<Integer> productCountsByProductIDs = List.of(3, 15, 12);
        List<BigDecimal> priceAmountsByProductsIDs = List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(5), BigDecimal.valueOf(100));
        BigDecimal spend = BigDecimal.valueOf(1305);

        when(ordersService.getOrderIDsCustomer((Long) any())).thenReturn(orderIDsCustomer);
        when(itemService.getProductIDsByOrderIDs((java.util.List<Long>) any())).thenReturn(productIDsByOrderIDs);
        when(itemService.getProductCountsByProductIDs((java.util.List<Long>) any()))
                .thenReturn(productCountsByProductIDs);
        when(priceService.getPriceAmountsByProductsIDs((java.util.List<Long>) any()))
                .thenReturn(priceAmountsByProductsIDs);
        when(customerService.calculateSpend((java.util.List<Long>) any(), (java.util.List<Integer>) any(),
                (java.util.List<BigDecimal>) any())).thenReturn(spend);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/orders/orders/customers/{customerID}/spend/", customerID);
        MockMvcBuilders.standaloneSetup(this.ordersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(spend.toString()));
    }

    @Test
    void canGetTotalSalesOfProduct() throws Exception {
        Long productID = 2L;
        Price price = new Price(productID, BigDecimal.valueOf(42));
        Integer countByProduct = 100;
        BigDecimal totalSales = BigDecimal.valueOf(4200);

        when(priceService.findPrice((Long) any())).thenReturn(price);
        when(itemService.getCountByProduct((Long) any())).thenReturn(countByProduct);
        when(productService.calculateTotalSales((Integer) any(), (BigDecimal) any()))
                .thenReturn(totalSales);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/orders/orders/products/{productID}/sales/", productID);
        MockMvcBuilders.standaloneSetup(ordersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(totalSales.toString()));
    }

}