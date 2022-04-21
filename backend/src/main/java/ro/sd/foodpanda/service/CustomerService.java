package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.CustomerDTO;
import ro.sd.foodpanda.model.Customer;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.repository.CustomerRepository;
import ro.sd.foodpanda.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Validator validator = new Validator();

    private Mappers mappers = new Mappers();

    public Customer save(Customer customer) throws Exception {
        List<CustomerDTO> customers = findAll();
        for (CustomerDTO customer1 : customers) {
            if (customer1.getUsername().equals(customer.getUsername())){
                throw new IllegalArgumentException("User already enrolled in application!");
            }
        }
        String encryptedPass = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        customer.setPassword(encryptedPass);
        validator.validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer findByUsername(String username) {
        return customerRepository.findCustomersByUsername(username);
    }

    public CustomerDTO findCustomer(String username, String password) throws Exception {
        CustomerDTO matchingCustomer = mappers.convertCustomerToDTO(customerRepository.findCustomersByUsername(username));
        if (matchingCustomer != null) {
            if (BCrypt.checkpw(password, matchingCustomer.getPassword())) {
                return matchingCustomer;
            } else {
                throw new IllegalArgumentException("Wrong password!");
            }
        } else {
            throw new IllegalArgumentException("The user does not exist!");
        }
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = new ArrayList<>();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Iterable<Customer> all = customerRepository.findAll();
        all.forEach(customers::add);
        for (Customer customer : customers) {
            customerDTOList.add(mappers.convertCustomerToDTO(customer));
        }
        return customerDTOList;
    }
}
