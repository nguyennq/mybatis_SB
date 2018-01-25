package vn.nguyen.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vn.nguyen.Mapper.CustomerMapper;
import vn.nguyen.domain.Customer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
    public void findCustomerByIdService() throws Exception {
        //given
        Customer customer = preparedCustomer();
        given(customerMapper.findByCustomerById(customer.getId())).willReturn(customer);
        //when
        Customer customerByIdService = customerServiceImpl.findCustomerByIdService(customer.getId());
        assertThat(customerByIdService.getId()).isEqualTo(customer.getId());
    }


    private Customer preparedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");
        customer.setEmail("abc@abc.vn");
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        customer.setDate(timestamp);
        return customer;
    }

}