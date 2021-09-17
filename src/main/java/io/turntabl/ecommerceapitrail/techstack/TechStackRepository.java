package io.turntabl.ecommerceapitrail.techstack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack, Long> {

    @Query("SELECT t FROM TechStack t WHERE t.name = ?1")
    Optional<TechStack> findTechStackByName(String name);
}
