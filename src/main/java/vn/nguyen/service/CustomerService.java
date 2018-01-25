package vn.nguyen.service;

import vn.nguyen.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
public interface CustomerService {
    void createCustomer(Customer customer);
    Customer findCustomerByIdService(Long id);
    List<Customer> findAllCustomer();
}
