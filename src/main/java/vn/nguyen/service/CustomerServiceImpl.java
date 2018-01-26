package vn.nguyen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import vn.nguyen.Mapper.CustomerMapper;
import vn.nguyen.Response.BaseResponse;
import vn.nguyen.domain.Customer;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.exception.CustomerNotFoundException;
import vn.nguyen.request.CustomerRequest;
import vn.nguyen.utils.ErrorCodeConstants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }



    @Override
    public BaseResponse<Customer> createCustomer(CustomerRequest customerRequest) throws CustomerIDExistException{
        Customer customer = customerMapper.findByCustomerById(customerRequest.getId());
        if(customer !=null){
            throw new CustomerIDExistException(customer.getId());
        }else {
            Customer customerNew = new Customer();
            customerNew.setId(customerRequest.getId());
            customerNew.setName(customerRequest.getName());
            customerNew.setEmail(customerRequest.getEmail());
            Date now = new Date();
            Timestamp timestamp = new Timestamp(now.getTime());
            customerNew.setDate(timestamp);
            customerMapper.addCustomer(customerNew.getId(), customerNew.getName(), customerNew.getEmail(), customerNew.getDate());
            return new BaseResponse<>();
        }


    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public BaseResponse<Customer> findCustomerByIdService(Long id) throws CustomerNotFoundException {
        Customer customerById = customerMapper.findByCustomerById(id);
        if(customerById==null){
            throw new CustomerNotFoundException(id);
        }
        else {
            return new BaseResponse<>(customerById);
        }
    }

    @Override
    public void deleteByUserIdService(Long id) {

    }

    @Override
    public List<Customer> findAllCustomer() {
        return null;
    }

    @Override
    public boolean checkEmailIsUnique(String name, String email) {
        return false;
    }
}
