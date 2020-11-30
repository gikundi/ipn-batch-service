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

                if (customers.size() > 2) {
                    List<Customers> limitedCustomerNumber = customers.subList(0, 2);
                    updateRecords(limitedCustomerNumber);

                } else {
                    updateRecords(customers);

                }
            } else {
                System.out.println("Finished Reading Records================");
            }

        } catch (Exception e) {
            System.out.println(" Exception ======" + e.toString());
        }

    }

    private void updateRecords(List<Customers> limitedCustomerNumber) {

        for (Customers cst : limitedCustomerNumber) {
            cst.setStatus(1);
            customerRepository.save(cst);

            System.out.println("Update Record================" + cst.getId());
        }
        System.out.println("Updated Result Set========================" + limitedCustomerNumber.size());
        sendIPN(limitedCustomerNumber);
    }

    private void sendIPN (List<Customers> limitedCustomerNumber) {
        System.out.println("Web hook initialized with data ========================" + limitedCustomerNumber);
        try {
            IPNThread ipnThread = new IPNThread(limitedCustomerNumber);
            Thread t = new Thread(ipnThread);
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
