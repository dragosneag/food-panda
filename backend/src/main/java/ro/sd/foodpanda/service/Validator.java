package ro.sd.foodpanda.service;

import ro.sd.foodpanda.model.Customer;

import java.util.regex.Pattern;

public class Validator {

    private static final String namePattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*";

    public void validateCustomer(Customer customer) {

        Pattern pattern = Pattern.compile(namePattern);
        if (!pattern.matcher(customer.getName()).matches()) {
            throw new IllegalArgumentException("Please enter a valid name!");
        }
    }
}
