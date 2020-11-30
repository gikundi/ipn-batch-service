package io.jenga.batch.utils;

import io.jenga.batch.dtos.CustomerDTO;
import io.jenga.batch.dtos.ResponseDTO;
import io.jenga.batch.entities.Customers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IPNThread implements Runnable {

    @Value("${ipn.url}")
    private String ipnUrl;

    private List<Customers> limitedCustomerNumber;

    public IPNThread(List<Customers> limitedCustomerNumber) {
        this.limitedCustomerNumber = limitedCustomerNumber;
    }

    @Override
    public void run() {

        System.out.println("Preparing IPN request*************************");


        List<CustomerDTO> customers = new ArrayList<>();
        for(Customers cust: limitedCustomerNumber) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setFirst_name(cust.getFirst_name());
            customerDTO.setSecond_name(cust.getSecond_name());
            customers.add(customerDTO);
            System.out.println("Sending IPN request*************************" + customerDTO);
        }

        try {

            String url = "https://webhook.site/f81ba7de-a6a9-4953-8a2e-5076df462ead";
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity httpEntity = new HttpEntity<>(customers);

            System.out.println("making http call for IPN: "+ url);

            ResponseEntity<ResponseDTO> responseDto = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseDTO.class);

            System.out.println("IPN Created......");
           // System.out.println("Response: "+ Objects.requireNonNull(responseDto.getBody()).getCode()+" "+responseDto.getBody().getStatus());

        }catch (Exception e){

            e.printStackTrace();
        }


    }
}
