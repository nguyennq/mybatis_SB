package vn.nguyen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nguyen.Response.BaseResponse;
import vn.nguyen.domain.Customer;
import vn.nguyen.exception.CustomerIDExistException;
import vn.nguyen.request.CustomerRequest;
import vn.nguyen.service.CustomerService;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by nals on 1/25/18.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public BaseResponse<Customer> getCustomerById(@PathVariable("id") Long id){
        return new BaseResponse(customerService.findCustomerByIdService(id));
    }

    @PostMapping
    public BaseResponse<Customer> createCustomerController(@Valid @RequestBody CustomerRequest customerRequest) throws CustomerIDExistException{
//        return new BaseResponse(customerService.createCustomer(customerRequest));
        return customerService.createCustomer(customerRequest);
    }

}
