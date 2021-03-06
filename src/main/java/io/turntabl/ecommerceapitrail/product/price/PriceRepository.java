package io.turntabl.ecommerceapitrail.product.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findFirstByProduct(Long productID);

    Boolean existsByProduct(Long productID);

    void deleteByProduct(Long productID);

    @Query("SELECT p.amount FROM Price p WHERE p.product IN (:ids) GROUP BY p.product")
    List<BigDecimal> findAllAmountsByProductsIDs(@Param("ids") List<Long>  productID);

}
