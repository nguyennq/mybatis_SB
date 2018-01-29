package vn.nguyen.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vn.nguyen.Mapper.CustomerMapper;
import vn.nguyen.Response.BaseResponse;
import vn.nguyen.domain.Customer;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.exception.CustomerNotFoundException;
import vn.nguyen.request.CustomerRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nals on 1/25/18.
 */
public class CustomerServiceImplTest {

    private CustomerServiceImpl customerServiceImpl;
    @Mock
    private CustomerMapper customerMapper;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerServiceImpl = new CustomerServiceImpl(customerMapper);
    }

    @Test
    public void findCustomerByIdService_ShouldReturnCustomerWhenCustomerIdFound() throws Exception {
        //given
        BaseResponse<Customer> customer = preparedCustomer(1L);
        given(customerMapper.findByCustomerById(customer.getBody().getId())).willReturn(customer.getBody());
        //when
        BaseResponse<Customer> customerByIdService = customerServiceImpl.findCustomerByIdService(customer.getBody().getId());
        assertThat(customerByIdService.getBody().getId()).isEqualTo(customer.getBody().getId());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findCustomerByIdService_ShouldThrowExceptionWhenCustomerIdNotFound() throws Exception {
        //given
        BaseResponse<Customer> customer = preparedCustomer(2L);
        given(customerMapper.findByCustomerById(customer.getBody().getId())).willReturn(null);
        //when
        BaseResponse<Customer> customerByIdService = customerServiceImpl.findCustomerByIdService(customer.getBody().getId());
        when(customerServiceImpl.findCustomerByIdService(customer.getBody().getId())).thenReturn(null);

    }

    @Test
    public void createCustomer_ShouldReturnSuccessfulWhenInputCorrectInformation() throws Exception {
        //given
        CustomerRequest customerRequest = preparedCustomerRequest();
        //when
        BaseResponse<Customer> customerService = customerServiceImpl.createCustomer(preparedCustomerRequest());
        //then
        assertThat(customerService.getBody().getId()).isEqualTo(customerRequest.getId());
        verify(customerMapper).addCustomer(customerRequest.getId(),customerRequest.getName(),customerRequest.getEmail(),customerService.getBody().getDate());
    }

    @Test(expected = CustomerIDExistException.class)
    public void createCustomer_ShouldReturnExceptionWhenInputCustomerIDExist() throws Exception {
        //given
        CustomerRequest customerRequest = preparedCustomerRequest();
        given(customerMapper.findByCustomerById(1L)).willThrow(new CustomerIDExistException(customerRequest.getId()));
        //when
        BaseResponse<Customer> customerService = customerServiceImpl.createCustomer(preparedCustomerRequest());
        //then
        assertThat(customerService.getBody().getId()).isEqualTo(customerRequest.getId());
        verify(customerMapper).addCustomer(customerRequest.getId(),customerRequest.getName(),customerRequest.getEmail(),customerService.getBody().getDate());
    }

    private CustomerRequest preparedCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(1L);
        customerRequest.setName("John");
        customerRequest.setEmail("abc@abc.vn");
        return customerRequest;
    }

    private BaseResponse<Customer> preparedCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("John");
        customer.setEmail("abc@abc.vn");
//        Date now = new Date();
//        Timestamp timestamp = new Timestamp(now.getTime());
//        customer.setDate(timestamp);
        return new BaseResponse<>(customer);
    }


//    private Customer preparedCustomer() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John");
//        customer.setEmail("abc@abc.vn");
//        Date now = new Date();
//        Timestamp timestamp = new Timestamp(now.getTime());
//        customer.setDate(timestamp);
//        return customer;
//    }

}