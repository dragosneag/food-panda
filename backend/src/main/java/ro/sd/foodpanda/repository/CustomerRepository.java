package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Customer;

public interface CustomerRepository extends AbstractRepository<Customer>{

    Customer findCustomersByUsername(String username);
}
