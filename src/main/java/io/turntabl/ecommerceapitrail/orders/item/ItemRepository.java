package io.turntabl.ecommerceapitrail.orders.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByQuantityGreaterThan(Integer base);

    List<Item> findAllByQuantityLessThan(Integer base);

    Optional<Item> findByOrder(Long orderID);

    Boolean existsByOrder(Long orderID);

    Optional<Item> findByProduct(Long orderID);

    Boolean existsByProduct(Long orderID);

    void deleteByOrder(Long orderID);

    void deleteAllByOrder(Long orderID);

    List<Item> findAllByOrder(Long orderID);

}
