package vn.nguyen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vn.nguyen.Mapper.CustomerMapper;
import vn.nguyen.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }



    @Override
    public void createCustomer(Customer customer) {

    }

    @Override
    public Customer findCustomerByIdService(Long id) {
        Customer customerById = customerMapper.findByCustomerById(id);
        return customerById;
    }

    @Override
    public List<Customer> findAllCustomer() {
        return null;
    }
}
