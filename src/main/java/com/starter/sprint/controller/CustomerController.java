package com.starter.sprint.controller;

import com.starter.sprint.entity.Customer;
import com.starter.sprint.utils.Utility;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@Log4j2
public class CustomerController {
    List<Customer> customers = new ArrayList<Customer>();

    @Autowired
    Utility utility;

    Logger logger = LogManager.getLogger(CustomerController.class);

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public HttpEntity getAllCustomers() {
        return new HttpEntity(customers, null);
    }

    @GetMapping(value = "/customer/email/{email}")
    public HttpEntity getCustomerByEmail(@PathVariable String email) {
        for (Customer cust : customers) {
            if (cust.getEmail().equals(email))
                return new HttpEntity(cust);
        }
        return new HttpEntity("No Records found for email: " + email);
    }

    @PostMapping("/customer/pool")
    public HttpEntity createCustomer(@RequestParam("poolSize") int poolSize) {

        for (int i = 0; i < poolSize; i++) {
            this.customers.add(Customer.builder().phone(Math.random()).companyName(utility.getRandomString())
                    .address(utility.getRandomString()).email(utility.getRandomString()).build());
        }
        return new HttpEntity(this.customers);
    }

    @PostMapping("/customer")
    public HttpEntity createCustomer(@RequestParam("email") String email) {

        Customer newCustomer = Customer.builder().email(email).build();
        customers.add(newCustomer);

        return new HttpEntity(newCustomer, null);
    }

    @DeleteMapping("/customer")
    public HttpEntity deleteCustomers(@RequestParam("email") String email) {

        Customer toDelete = new Customer();

        for (Customer cust : customers) {
            if (cust.getEmail().equals(email)) {
                toDelete = cust;
                customers.remove(cust);
                return new HttpEntity("Deleted the customer: " + cust.toString());
            }
        }

        return new HttpEntity("No customer found for email: " + email);
    }
}
