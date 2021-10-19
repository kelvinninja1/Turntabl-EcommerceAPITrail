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
        //TODO: Get orderId from Order items where product equals X - AS orderIds
        List<Long> orderIdsByProduct = itemService.getOrderIDsByProduct(productID);

        //TODO: Get customerId from Orders where OrderID in orderIds - AS CustomersIds
        List<Long> customerIDsByOrderIds = ordersService.getCustomerIDsByOrderIds(orderIdsByProduct);
        //TODO: OR
        //TODO: Foreach orderId In OrderIds Get CustomerIds & add to List - AS CustomersIds

        //TODO: Get CustomerList from Customers where CustomerId in customerIds - AS CustomerList
        //TODO: OR
        //TODO: Foreach orderId In OrderIds Get CustomerIds & add to List - AS CustomersIds

        return customerService.getCustomersIn(customerIDsByOrderIds);
    }

    @GetMapping("orders/customers/{customerID}/spend/")
    public ResponseEntity<BigDecimal> getTotalSpendOfCustomer(@NotNull @PathVariable("CustomerID") Long customerID){
        //TODO: Get OrderIDs from Orders where customerID equals X - AS OrderIds
        List<Long> orderIDs = ordersService.getOrderIDsCustomer(customerID);
        //TODO: Get and Count Distinct ProductID Group By Item Where OrderId In OrderIds - AS ProductIds
        List<Long> productIDsByOrderIDs = itemService.getProductIDsByOrderIDs(orderIDs);
        List<Integer> productCountsByProductIDs = itemService.getProductCountsByProductIDs(productIDsByOrderIDs);
        //TODO: Get Price from Prices where ProductID in Product Ids
        List<BigDecimal> priceAmountsByProductsIDs = priceService.getPriceAmountsByProductsIDs(productIDsByOrderIDs);
        //TODO: Multiply price by count then sum up
        BigDecimal customerSpend = customerService.calculateSpend(productIDsByOrderIDs, productCountsByProductIDs, priceAmountsByProductsIDs);
        return new ResponseEntity<BigDecimal> (customerSpend, HttpStatus.OK);
    }

    @GetMapping("orders/products/{productID}/sales/")
    public ResponseEntity<BigDecimal> getTotalSalesOfProduct(@NotNull @PathVariable("productID") Long productID){
        //TODO: Get and Count X ProductID
        Integer countByProduct = itemService.getCountByProduct(productID);
        //TODO: Get Price by ProductID
        BigDecimal price = priceService.findPrice(productID).getAmount();
        //TODO: Multiply price by count
        BigDecimal productSales = productService.calculateTotalSales(countByProduct, price);
        return new ResponseEntity<BigDecimal>(productSales, HttpStatus.OK);
    }


}