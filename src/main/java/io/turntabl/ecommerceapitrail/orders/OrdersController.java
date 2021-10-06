package io.turntabl.ecommerceapitrail.orders;

import io.turntabl.ecommerceapitrail.orders.item.Item;
import io.turntabl.ecommerceapitrail.orders.item.ItemService;
import io.turntabl.ecommerceapitrail.product.price.Price;
import io.turntabl.ecommerceapitrail.product.price.PriceService;
import io.turntabl.ecommerceapitrail.product.stock.Stock;
import io.turntabl.ecommerceapitrail.product.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ItemService itemService;
    private final StockService stockService;
    private final PriceService priceService;

    @Autowired
    public OrdersController(OrdersService ordersService, ItemService itemService, StockService stockService, PriceService priceService) {
        this.ordersService = ordersService;
        this.itemService = itemService;
        this.stockService = stockService;
        this.priceService = priceService;
    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> listOrders(){
        return ordersService.getOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addOrders(@RequestBody Orders order){
        Orders newOrder = ordersService.addOrders(order);
        return List.of("Success", newOrder);
    }

    @GetMapping(path = "{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public List getOrder(@PathVariable("orderID") Long orderID){
        Orders order = ordersService.getOrder(orderID);
        List<Item> orderItems = itemService.getOrderItems(orderID);
        return List.of(
                order,
                orderItems
        );
    }

    @DeleteMapping(path = "{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteOrder(@PathVariable("orderID") Long orderID){
        ordersService.deleteOrder(orderID);
        // Consider updating Stock
        itemService.deleteItem(orderID);
        return List.of("Success");
    }

    @GetMapping(path = "/stock")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> listOrdersStock(){
        return stockService.getStocks();
    }

    @GetMapping(path = "{orderID}/item")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addOrderItems(@RequestBody Item item){
        Item newItem = itemService.addItems(item);
        return List.of("Success", newItem);
    }
    @DeleteMapping(path = "items/{itemID}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteOrderItems(@PathVariable("itemID") Long itemID){
        itemService.deleteItem(itemID);
        return List.of("Success");
    }

    @PutMapping(path = "items/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object> updateItem(@PathVariable("productID") Long productID, @RequestBody Map<String, Object> change){
        itemService.updateItem(productID, change);
        return List.of("Success",
                change);
    }

}