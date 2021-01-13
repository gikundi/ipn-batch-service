package io.jenga.batch.utils;

import io.jenga.batch.dtos.CustomerDTO;
import io.jenga.batch.dtos.ResponseDTO;
import io.jenga.batch.entities.Customers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IPNThread implements Runnable {

    @Value("${ipn.url}")
    private String ipnUrl;

    private Customers customerData;

    public IPNThread(Customers customers) {
        this.customerData = customers;
    }

    @Override
    public void run() {

        System.out.println("Preparing IPN request*************************");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirst_name(customerData.getFirst_name());
        customerDTO.setSecond_name(customerData.getSecond_name());
        System.out.println("Sending IPN request*************************" + customerDTO);

        try {
            String url = ipnUrl;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
            SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate
                    .getRequestFactory();
            rf.setReadTimeout(10000);
            rf.setConnectTimeout(5000);

            HttpEntity httpEntity = new HttpEntity<>(customerDTO);
            System.out.println("making http call for IPN: "+ url);
            ResponseEntity<ResponseDTO> responseDto = restTemplate.exchange(url, HttpMethod.POST, httpEntity, ResponseDTO.class);
            System.out.println("IPN Created......" +responseDto);

        }catch (Exception e){

            e.printStackTrace();
        }


    }
}
