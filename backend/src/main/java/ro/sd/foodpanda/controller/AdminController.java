package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.DTO.AdminDTO;
import ro.sd.foodpanda.DTO.OrderDTO;
import ro.sd.foodpanda.model.Admin;
import ro.sd.foodpanda.model.Order;
import ro.sd.foodpanda.service.AdminService;
import ro.sd.foodpanda.service.OrderService;
import ro.sd.foodpanda.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/addadmin")
    public Admin newAdmin(@RequestBody Admin admin) {
        List<AdminDTO> admins = adminService.findAll();
        for (AdminDTO admin1 : admins) {
            if (admin1.getUsername().equals(admin.getUsername())){
                return null;
            }
        }
        String encryptedPass = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(encryptedPass);
        return adminService.save(admin);
    }

    @GetMapping("/admins")
    public List<AdminDTO> allUsers() {
        return adminService.findAll();
    }

    @GetMapping("/admins/login-admin")
    public AdminDTO loginAdmin(@RequestParam Map<String,String> requestParams) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");
        AdminDTO matchingAdmin = adminService.findByUsername(username);
        if (matchingAdmin != null) {
            if (BCrypt.checkpw(password, matchingAdmin.getPassword())) {
                return matchingAdmin;
            }
        }
        return null;
    }

    @GetMapping("/admin-orders")
    public List<OrderDTO> listOrders(@RequestBody Admin admin) {
        List<OrderDTO> orders = orderService.findAll();
        List<OrderDTO> adminOrders = new ArrayList<>();
        for (OrderDTO order : orders) {
            if (order.getRestaurant().equals(admin.getRestaurant().getName())) {
                adminOrders.add(order);
            }
        }
        return adminOrders;
    }
}
