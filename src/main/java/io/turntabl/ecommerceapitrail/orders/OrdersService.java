package io.turntabl.ecommerceapitrail.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public Orders addOrders(Orders order) {
        if (order == null) {
            throw new IllegalStateException("Order details are empty");
        }
        ordersRepository.save(order);
        return order;
    }

    public Orders getOrder(Long orderID) {
        Orders order = ordersRepository.findById(orderID).orElseThrow(() -> new IllegalStateException("Order with ID:" + orderID + " does not exist"));
        return order;
    }

    public void deleteOrder(Long orderID) {
        boolean exists = ordersRepository.existsById(orderID);
        if (!exists) {
            throw new IllegalStateException("Order with ID:" + orderID + " does not exist");
        }
        ordersRepository.deleteById(orderID);
    }

    @Transactional
    public void updateOrder(Long orderID, Map<String, Object> change) {
        Orders order = ordersRepository.findById(orderID).orElseThrow(() -> new IllegalStateException("Order with ID:" + orderID + " does not exist"));

        Long customer = Long.valueOf(change.get("customer").toString());
        if ( !Objects.equals(customer, order.getCustomer())) {
            order.setCustomer(customer);
            order.setDateModified(LocalDate.now());
        }
    }

    public List<Orders> getOrdersOverLastXMonths(Integer months) {
//        LocalDate startFrom = LocalDate.now().minusMonths(months);
        return ordersRepository.findAllByDateAddedAfter(LocalDate.now().minusMonths(months));
    }

    public List<Orders> getCustomersWithMultipleOrders() {
        return ordersRepository.findAllCustomersWithMultipleOrders();
    }

    public List<Long> getCustomerIDsByOrderIds(List<Long> orderIdsByProduct) {
       return ordersRepository.findAllCustomerIDsByOrderIds(orderIdsByProduct);
    }

    public List<Long> getOrderIDsCustomer(Long customerID) {
        return ordersRepository.findAllOrderIDsByCustomer(customerID);
    }
}
