package mk.shop.repository;

import mk.shop.entity.Order;
import mk.shop.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByCreatedBetween(LocalDateTime from, LocalDateTime to);
}
