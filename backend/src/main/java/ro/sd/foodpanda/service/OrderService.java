package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.FoodDTO;
import ro.sd.foodpanda.DTO.OrderDTO;
import ro.sd.foodpanda.model.*;
import ro.sd.foodpanda.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodService foodService;

    private Mappers mappers = new Mappers();

    public String save(OrderDTO order) throws Exception {
        Order orderToSave = new Order();
        orderToSave.setStatus(Status.valueOf(order.getStatus()));
        orderToSave.setPrice(Integer.valueOf(order.getPrice()));
        orderToSave.setPlacingDate(LocalDate.parse(order.getPlacingDate()));
        orderToSave.setPlacingTime(LocalTime.parse(order.getPlacingTime()));

        List<Food> foodList = new ArrayList<>();
        Customer customer = customerService.findByUsername(order.getCustomer());
        Restaurant restaurant = restaurantService.findByName(order.getRestaurant());
        for (FoodDTO food : order.getFoodList()) {
            foodList.add(foodService.findFoodByName(food.getName()));
        }
        orderToSave.setFoodList(foodList);
        orderToSave.setCustomer(customer);
        orderToSave.setRestaurant(restaurant);
        orderRepository.save(orderToSave);
        return "Success";
    }

    public List<OrderDTO> findAll() {
        List<Order> orders = new ArrayList<>();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Iterable<Order> all = orderRepository.findAll();
        all.forEach(orders::add);
        for (Order order : orders) {
            orderDTOList.add(mappers.convertOrderToDTO(order));
        }
        return orderDTOList;
    }

    public Order findOrderById(Integer id) {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public List<OrderDTO> findActiveOrders(String customerUsername) {
        List<OrderDTO> orderDTOList = findAll();
        List<OrderDTO> activeOrders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList) {
            if (!orderDTO.getStatus().equals("DELIVERED") && !orderDTO.getStatus().equals("DECLINED") && orderDTO.getCustomer().equals(customerUsername)) {
                activeOrders.add(orderDTO);
            }
        }
        return activeOrders;
    }

    public List<OrderDTO> findNonactiveOrders(String customerUsername) {
        List<OrderDTO> orderDTOList = findAll();
        List<OrderDTO> nonactiveOrders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList) {
            if ((orderDTO.getStatus().equals("DELIVERED") || orderDTO.getStatus().equals("DECLINED")) && orderDTO.getCustomer().equals(customerUsername)) {
                nonactiveOrders.add(orderDTO);
            }
        }
        return nonactiveOrders;
    }

    public List<OrderDTO> findRestaurantActiveOrders(String restaurantName) {
        List<OrderDTO> orderDTOList = findAll();
        List<OrderDTO> activeOrders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList) {
            if (!orderDTO.getStatus().equals("DELIVERED") && !orderDTO.getStatus().equals("DECLINED") && orderDTO.getRestaurant().equals(restaurantName)) {
                activeOrders.add(orderDTO);
            }
        }
        return activeOrders;
    }

    public List<OrderDTO> findRestaurantNonactiveOrders(String restaurantName) {
        List<OrderDTO> orderDTOList = findAll();
        List<OrderDTO> nonactiveOrders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList) {
            if ((orderDTO.getStatus().equals("DELIVERED") || orderDTO.getStatus().equals("DECLINED")) && orderDTO.getRestaurant().equals(restaurantName)) {
                nonactiveOrders.add(orderDTO);
            }
        }
        return nonactiveOrders;
    }

    public String update(String orderId, String status) {
        Order order = findOrderById(Integer.valueOf(orderId));
        if (order != null) {
            order.setStatus(Status.valueOf(status));
            orderRepository.save(order);
            return "Success";
        }
        return "Failure";
    }

    public List<OrderDTO> getFilteredOrders(String restaurant, String status) {
        List<OrderDTO> orders = findAll();
        List<OrderDTO> filteredOrders = new ArrayList<>();
        for (OrderDTO order : orders) {
            if (order.getStatus().equals(status) && order.getRestaurant().equals(restaurant)){
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
}
