package io.turntabl.ecommerceapitrail.orders;

import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Orders> addOrders(Orders order) {
        if (order.getCustomer() != null) {
            Orders newOrder = ordersRepository.save(order);
            return new ResponseEntity<Orders>(newOrder, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Order details are empty");
        }
    }

    public Orders getOrder(Long orderID) {
        Orders order = ordersRepository.findById(orderID).orElseThrow(() -> new NotFoundException("Order with ID:" + orderID + " does not exist"));
        return order;
    }

    public ResponseEntity<Orders> deleteOrder(Long orderID) {
        checkIfOrderExists(orderID);
        ordersRepository.deleteById(orderID);
        return new ResponseEntity<Orders> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Orders>> getOrdersOverLastXMonths(Integer months) {
        List<Orders> allByDateAddedAfter = ordersRepository.findAllByDateAddedAfter(LocalDate.now().minusMonths(months));
        return new ResponseEntity<List<Orders>> (allByDateAddedAfter, HttpStatus.OK);
    }

    public ResponseEntity<List<Orders>> getCustomersWithMultipleOrders() {
        List<Orders> allCustomersWithMultipleOrders = ordersRepository.findAllCustomersWithMultipleOrders();
        return new ResponseEntity<List<Orders>> (allCustomersWithMultipleOrders, HttpStatus.OK);
    }

    private void checkIfOrderExists(Long orderID) {
        boolean exists = ordersRepository.existsById(orderID);
        if (!exists) {
            throw new NotFoundException("Order with ID:" + orderID + " does not exist");
        }
    }

    public List<Long> getCustomerIDsByOrderIds(List<Long> orderIdsByProduct) {
       return ordersRepository.findAllCustomerIDsByOrderIds(orderIdsByProduct);
    }

    public List<Long> getOrderIDsCustomer(Long customerID) {
        return ordersRepository.findAllOrderIDsByCustomer(customerID);
    }
}
