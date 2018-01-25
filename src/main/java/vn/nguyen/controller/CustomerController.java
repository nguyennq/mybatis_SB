package vn.nguyen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nguyen.domain.Customer;
import vn.nguyen.service.CustomerService;

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
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.findCustomerByIdService(id));
    }
}
