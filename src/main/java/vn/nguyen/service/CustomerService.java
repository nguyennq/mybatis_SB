package vn.nguyen.service;

import vn.nguyen.Response.BaseResponse;
import vn.nguyen.domain.Customer;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.exception.CustomerNotFoundException;
import vn.nguyen.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
public interface CustomerService {
    BaseResponse<Customer> createCustomer(CustomerRequest customerRequest) throws CustomerIDExistException;
    Customer updateCustomer(Customer customer);
    BaseResponse<Customer> findCustomerByIdService(Long id) throws CustomerNotFoundException;
    void deleteByUserIdService(Long id);
    List<Customer> findAllCustomer();
    boolean checkEmailIsUnique(String name, String email);
}
