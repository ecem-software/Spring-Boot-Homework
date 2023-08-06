package com.example.erp_system.controller;

import com.example.erp_system.dto.CustomerDTO;
import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
// create-update-delete-get-getAll and methods are written.
    @PostMapping("create")
    public ResponseEntity<Boolean> createCustomer(@RequestBody CustomerDTO customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer.getName(), customer.getSurname(), customer.getEmail(), customer.getCustomerNo()),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<CustomerEntity>> getAllByName(@PathVariable String name) {
        return new ResponseEntity<>(customerService.getAllByNameIContains(name), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @PutMapping("update/{uuid}")
    public ResponseEntity<Boolean> updateCustomerByUuid(@PathVariable UUID uuid, @RequestBody CustomerDTO customer) {
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customer.getName());
        newCustomer.setEmail(customer.getSurname());
        newCustomer.setEmail(newCustomer.getEmail());
        newCustomer.setCustomerNo(newCustomer.getCustomerNo());
        return new ResponseEntity<>(customerService.UpdateCustomer(uuid, newCustomer), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @DeleteMapping("delete_customer/{uuid}")
    public ResponseEntity<Boolean> deleteCustomerByUuid(@PathVariable UUID uuid){
        return new ResponseEntity<>(customerService.deleteCustomer(uuid), HttpStatus.OK);
    }


}
