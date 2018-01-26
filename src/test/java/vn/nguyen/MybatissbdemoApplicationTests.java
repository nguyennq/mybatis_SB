package vn.nguyen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.nguyen.Mapper.CustomerMapper;
import vn.nguyen.domain.Customer;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatissbdemoApplicationTests {

//	@Autowired
//	private CustomerMapper customerMapper;


	@Test
	public void contextLoads() {
//		Customer customer = preparedCustomer();
//		customerMapper.addCustomer(customer.getId(),customer.getName(),customer.getEmail(),customer.getDate());

	}

//	private Customer preparedCustomer() {
//		Customer customer = new Customer();
//		customer.setId(1L);
//		customer.setName("John");
//		customer.setEmail("abc@abc.vn");
//		Date now = new Date();
//		Timestamp timestamp = new Timestamp(now.getTime());
//		customer.setDate(timestamp);
//
//		return customer;
//	}

}
