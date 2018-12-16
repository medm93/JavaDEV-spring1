package com.pgs.java_dev.controllers;

import com.pgs.java_dev.model.Customer;
import com.pgs.java_dev.model.Product;
import com.pgs.java_dev.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllProduct() {
        return customerService.findAllProduct();
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Customer customer) {
        return customerService.createProduct(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findProductById(@PathVariable Long id) {
        return customerService.findProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateProductById(id, customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        return customerService.deleteProductById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findAllCustomerProducts(@PathVariable Long id) {
        return customerService.findAllCustomerProducts(id);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Customer> addProductToCustomerById(@PathVariable Long id, @RequestBody Product product) {
        return customerService.addProductToCustomerById(id, product);
    }
}
