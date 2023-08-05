package com.example.erp_system.service;

import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

//Create Customer Method

    public boolean createCustomer(String name, String surname, String email, String customerNo) {
        if (name == null || surname == null || email == null || customerNo == null) {
            return false;
        } else {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setName(name);
            customerEntity.setSurname(surname);
            customerEntity.setEmail(email);
            customerEntity.setCustomerNo(customerNo);
            customerRepository.save(customerEntity);
            return true;
        }
    }

    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    public List<CustomerEntity> getAllByNameIContains(String name) {
        return customerRepository.findAllByNameContainsIgnoreCase(name);
    }

    // Customers update method are created.
    public boolean UpdateCustomer(UUID uuid, CustomerEntity customerEntity) {
        if (uuid == null || customerEntity == null) {
            return false;
        } else {
            CustomerEntity existingCustomer = customerRepository.findByUuid(uuid);
            if (existingCustomer == null)
                return false;
            existingCustomer.setName(customerEntity.getName());
            existingCustomer.setSurname(customerEntity.getSurname());
            existingCustomer.setEmail(customerEntity.getEmail());
            existingCustomer.setCustomerNo(customerEntity.getCustomerNo());
            customerRepository.save(existingCustomer);
            return true;
        }

    }
    public boolean deleteCustomer(UUID uuid) {
        if (uuid == null)
            return false;
        customerRepository.deleteByUuid(uuid);
        return true;
    }
}
