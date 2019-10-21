package mk.shop.repository;

import mk.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByDeletedIsFalse();
    Optional<Product> findBySkuAndDeletedIsFalse(Long sku);
}
