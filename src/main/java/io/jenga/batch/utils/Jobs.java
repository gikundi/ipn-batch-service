package io.jenga.batch.utils;


import io.jenga.batch.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Jobs {

    @Autowired
    CustomersService customersService;

    @Scheduled(cron = "0 0/2 * * * *")  //runs after every minutes
    public void updateCustomers(){
        customersService.updateCustomers();
    }

}
