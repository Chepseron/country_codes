package com.amon.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amon.demo.exception.RecordNotFoundException;
import com.amon.demo.model.CustomerEntity;
import com.amon.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> result = (List<CustomerEntity>) repository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<CustomerEntity>();
        }
    }

    public CustomerEntity getCustomerById(Long id) throws RecordNotFoundException {
        Optional<CustomerEntity> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }
}
