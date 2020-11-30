package io.jenga.batch.controllers;


import io.jenga.batch.entities.Customers;
import io.jenga.batch.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")

public class ApiController {
    @Autowired
    CustomersService customersService;

    @GetMapping("/customers")
    public List<Customers> customersList() {
        return customersService.fetchCustomers();
    }
}
