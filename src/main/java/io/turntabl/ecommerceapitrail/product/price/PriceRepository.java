package io.turntabl.ecommerceapitrail.product.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findByProduct(Long productID);

    Boolean existsByProduct(Long productID);

    void deleteByProduct(Long productID);

}
