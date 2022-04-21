package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.DTO.CustomerDTO;
import ro.sd.foodpanda.model.Customer;
import ro.sd.foodpanda.service.CustomerService;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/addcustomer")
    public String registerCustomer(@RequestBody Customer customer) {
        try {
            customerService.save(customer);
        } catch (Exception e) {
            String errorMessage = "{\"error\":\"" + e.getMessage() + "\"}";
            return errorMessage;
        }
        return "{\"success\":\"Successful save!\"}";
    }

    @GetMapping("/customers")
    public List<CustomerDTO> allCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/customers/login-customer")
    public CustomerDTO loginCustomer(@RequestParam Map<String,String> requestParams) {
        String username = requestParams.get("username");
        String password = requestParams.get("password");
        try {
            return customerService.findCustomer(username, password);
        } catch (Exception e) {
            String errorMessage = "{\"error\":\"" + e.getMessage() + "\"}";
        }
        return null;
    }

}