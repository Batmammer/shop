package mk.shop.rest.controller;

import mk.shop.entity.Order;
import mk.shop.entity.Product;
import mk.shop.service.OrderService;
import mk.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<Order> listOrders(@RequestParam("from")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                  @RequestParam("to")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return orderService.listOrders(from, to);
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order newOrder) {
        return orderService.createOrder(newOrder);
    }
}
