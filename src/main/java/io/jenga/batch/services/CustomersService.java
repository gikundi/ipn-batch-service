package io.jenga.batch.services;

import io.jenga.batch.entities.Customers;
import io.jenga.batch.repositories.CustomerRepository;
import io.jenga.batch.utils.IPNThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customers> fetchCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomers() {

        try {
            List<Customers> customers = customerRepository.findByStatus(0);
            if (customers.size()!= 0) {
                updateRecords(customers);
            } else {
                System.out.println("Finished Reading Records================");
            }

        } catch (Exception e) {
            System.out.println(" Exception ======" + e.toString());
        }

    }

    private void updateRecords(List<Customers> customers) {

        for (Customers cst : customers) {
            cst.setStatus(1);
            customerRepository.save(cst);
            System.out.println("Update Record================" + cst.getId());
            sendIPN(cst);
        }
        System.out.println("Updated Result Set========================" + customers.size());

    }

    private void sendIPN (Customers customers) {
        System.out.println("Web hook initialized with data ========================" + customers);
        try {
            IPNThread ipnThread = new IPNThread(customers);
            Thread t = new Thread(ipnThread);
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
