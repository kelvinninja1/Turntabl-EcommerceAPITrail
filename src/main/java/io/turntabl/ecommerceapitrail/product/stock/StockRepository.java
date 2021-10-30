package io.turntabl.ecommerceapitrail.product.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByQuantityGreaterThan(Integer base);

    List<Stock> findAllByQuantityLessThan(Integer base);

    Optional<Stock> findByProduct(Long productID);

    Boolean existsByProduct(Long productID);

    void deleteByProduct(Long productID);
}
