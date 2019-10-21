package mk.shop.service;

import mk.shop.entity.Order;
import mk.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public List<Order> listOrders(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAllByCreatedBetween(from, to).stream().map(o -> o.updateTotal()).collect(Collectors.toList());
    }

    public Order createOrder(Order newOrder) {
        Order orderToSave = new Order(newOrder.getEmail());
        orderToSave.setProducts(newOrder.getProducts().stream().map(product -> productService.findNotDeletedProduct(product.getSku())).collect(Collectors.toList()));
        return orderRepository.save(orderToSave);
    }
}
