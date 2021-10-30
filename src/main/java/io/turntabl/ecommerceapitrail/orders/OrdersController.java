package io.turntabl.ecommerceapitrail.orders;

import com.sun.istack.NotNull;
import io.turntabl.ecommerceapitrail.customer.Customer;
import io.turntabl.ecommerceapitrail.customer.CustomerService;
import io.turntabl.ecommerceapitrail.orders.item.Item;
import io.turntabl.ecommerceapitrail.orders.item.ItemService;
import io.turntabl.ecommerceapitrail.product.ProductService;
import io.turntabl.ecommerceapitrail.product.price.PriceService;
import io.turntabl.ecommerceapitrail.product.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ItemService itemService;
    private final StockService stockService;
    private final PriceService priceService;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrdersController(OrdersService ordersService, ItemService itemService, StockService stockService, PriceService priceService, CustomerService customerService, ProductService productService) {
        this.ordersService = ordersService;
        this.itemService = itemService;
        this.stockService = stockService;
        this.priceService = priceService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Orders>> listOrders(){
        return ordersService.getOrders();
    }

    @PostMapping
    public ResponseEntity<Orders> addOrders(@NotNull @RequestBody Orders order){
        customerService.checkIfCustomerExists(order.getCustomer());
        return ordersService.addOrders(order);
    }

    @GetMapping(path = "{orderID}")
    public ResponseEntity<Object> getOrder(@NotNull @PathVariable("orderID") Long orderID){
        Orders order = ordersService.getOrder(orderID);
        List<Item> orderItems = itemService.getOrderItems(orderID);

        return new  ResponseEntity<Object>(
                List.of(
                ordersService.getOrder(orderID),
                itemService.getOrderItems(orderID)
                ),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "{orderID}")
    public ResponseEntity<Orders> deleteOrder(@NotNull @PathVariable("orderID") Long orderID){
        //TODO: Consider updating Stock
        itemService.deleteItems(orderID);
        return ordersService.deleteOrder(orderID);
    }

    @DeleteMapping(path = "items/{itemID}")
    public ResponseEntity<Item> deleteOrderItems(@NotNull @PathVariable("itemID") Long itemID){
        return itemService.deleteItem(itemID);
    }

    @PutMapping(path = "items/{itemID}")
    public ResponseEntity<Item> updateItem(@NotNull @PathVariable("itemID") Long itemID, @NotNull @RequestBody Item updatedItem){
        return itemService.updateItem(itemID, updatedItem);
    }


    @GetMapping("orders/last/{months}/months")
    public ResponseEntity<List<Orders>> listOrdersOverLastXMonths(@NotNull @PathVariable("months") Integer months){
        return ordersService.getOrdersOverLastXMonths(months);
    }


    @GetMapping("orders/multiple/customers/")
    public ResponseEntity<List<Orders>> listCustomersWithMultipleOrders(){
        return ordersService.getCustomersWithMultipleOrders();
    }

    @GetMapping("orders/products/{productID}/customers/")
    public ResponseEntity<List<Customer>> listCustomersWhoPurchasedProductX(@NotNull @PathVariable("productID") Long productID){
        List<Long> orderIdsByProduct = itemService.getOrderIDsByProduct(productID);
        List<Long> customerIDsByOrderIds = ordersService.getCustomerIDsByOrderIds(orderIdsByProduct);
        return customerService.getCustomersIn(customerIDsByOrderIds);
    }

    @GetMapping("orders/customers/{customerID}/spend/")
    public ResponseEntity<BigDecimal> getTotalSpendOfCustomer(@NotNull @PathVariable("customerID") Long customerID){
        List<Long> orderIDs = ordersService.getOrderIDsCustomer(customerID);
        List<Long> productIDsByOrderIDs = itemService.getProductIDsByOrderIDs(orderIDs);
        List<Integer> productCountsByProductIDs = itemService.getProductCountsByProductIDs(productIDsByOrderIDs);
        List<BigDecimal> priceAmountsByProductsIDs = priceService.getPriceAmountsByProductsIDs(productIDsByOrderIDs);
        BigDecimal customerSpend = customerService.calculateSpend(productIDsByOrderIDs, productCountsByProductIDs, priceAmountsByProductsIDs);
        return new ResponseEntity<BigDecimal> (customerSpend, HttpStatus.OK);
    }

    @GetMapping("orders/products/{productID}/sales/")
    public ResponseEntity<BigDecimal> getTotalSalesOfProduct(@NotNull @PathVariable("productID") Long productID){
        Integer countByProduct = itemService.getCountByProduct(productID);
        BigDecimal price = priceService.findPrice(productID).getAmount();
        BigDecimal productSales = productService.calculateTotalSales(countByProduct, price);
        return new ResponseEntity<BigDecimal>(productSales, HttpStatus.OK);
    }


}