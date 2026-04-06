package sn.isi.l3gl.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.isi.l3gl.core.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    long countByQuantityLessThanEqual(int threshold);
}
