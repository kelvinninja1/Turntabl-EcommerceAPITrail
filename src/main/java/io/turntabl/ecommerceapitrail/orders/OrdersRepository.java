package io.turntabl.ecommerceapitrail.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByDateAddedAfter(LocalDate monthsInDate);

    @Query("SELECT o.customer, COUNT(o.customer) FROM Orders AS o GROUP BY o.customer ORDER BY o.customer DESC")
    List<Orders> findAllCustomersWithMultipleOrders();
}
