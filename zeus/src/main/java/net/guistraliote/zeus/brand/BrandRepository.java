package net.guistraliote.zeus.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);
    Optional<Brand> findBrandByName(String name);
}
