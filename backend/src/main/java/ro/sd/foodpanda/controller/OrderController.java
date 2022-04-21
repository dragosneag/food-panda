package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.DTO.OrderDTO;
import ro.sd.foodpanda.model.Food;
import ro.sd.foodpanda.model.Order;
import ro.sd.foodpanda.model.Status;
import ro.sd.foodpanda.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/addorder")
    public String newOrder(@RequestBody OrderDTO order) {
        try {
            orderService.save(order);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    @GetMapping("/orders")
    public List<OrderDTO> allOrders() {
        return orderService.findAll();
    }

    @GetMapping("/filtered-orders/{restaurant}/{status}")
    public List<OrderDTO> filterOrders(@PathVariable String restaurant, @PathVariable String status) {
        return orderService.getFilteredOrders(restaurant, status);
    }

    @PutMapping(value = "/change-order/{id}/{status}")
    public String changeOrderStatus(@PathVariable String id, @PathVariable String status) {
        orderService.update(id, status);
        return "Success";
    }

    @GetMapping("/filtered-orders/active-orders/{username}")
    public List<OrderDTO> getActiveOrders(@PathVariable String username) {
        return orderService.findActiveOrders(username);
    }

    @GetMapping("/filtered-orders/nonactive-orders/{username}")
    public List<OrderDTO> getNonactiveOrders(@PathVariable String username) {
        return orderService.findNonactiveOrders(username);
    }

    @GetMapping("/filtered-orders/restaurant-active-orders/{name}")
    public List<OrderDTO> getRestaurantActiveOrders(@PathVariable String name) {
        return orderService.findRestaurantActiveOrders(name);
    }

    @GetMapping("/filtered-orders/restaurant-nonactive-orders/{name}")
    public List<OrderDTO> getRestaurantNonactiveOrders(@PathVariable String name) {
        return orderService.findRestaurantNonactiveOrders(name);
    }
}
