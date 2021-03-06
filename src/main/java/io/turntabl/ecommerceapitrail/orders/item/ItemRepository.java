package io.turntabl.ecommerceapitrail.orders.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByQuantityGreaterThan(Integer base);

    List<Item> findAllByQuantityLessThan(Integer base);

    Optional<Item> findByOrderID(Long orderID);

    Boolean existsByOrderID(Long orderID);

    Optional<Item> findByProduct(Long orderID);

    Boolean existsByProduct(Long orderID);

    void deleteByOrderID(Long orderID);

    void deleteAllByOrderID(Long orderID);

    List<Item> findAllByOrderID(Long orderID);

    @Query("SELECT i.Id FROM Item i WHERE i.product = ?1")
    List<Long> findAllIDsByProduct(Long productID);

    @Query("SELECT i.product FROM Item i WHERE i.orderID IN (:ids) GROUP BY i.product")
    List<Long> findAllDistinctProductByOrderIDs(@Param("ids") List<Long>  orderID);

    @Query("SELECT COUNT(i.product) FROM Item i WHERE i.product IN (:ids) GROUP BY i.product")
    List<Integer> CountProductByProductIDs(@Param("ids") List<Long>  productID);

    Integer countAllByProduct(Long productID);

}
