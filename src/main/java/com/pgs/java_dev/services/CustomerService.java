package com.pgs.java_dev.services;

import com.pgs.java_dev.model.Customer;
import com.pgs.java_dev.model.Product;
import com.pgs.java_dev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> findAllProduct() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    public ResponseEntity<Object> createProduct(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Long id = customerRepository.save(customer).getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    public ResponseEntity<Customer> findProductById(Long id) {
        Optional<Customer> searchResult = customerRepository.findById(id);
        if (!searchResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customer = searchResult.get();
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Object> updateProductById(Long id, Customer customer) {
        Optional<Customer> searchResult = customerRepository.findById(id);
        if (!searchResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customerInDB = searchResult.get();
        customerInDB.setFirstName(customer.getFirstName());
        customerInDB.setLastName(customer.getLastName());
        customerInDB.setEmail(customer.getEmail());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> deleteProductById(Long id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Product>> findAllCustomerProducts(Long id) {
        Optional<Customer> searchProduct = customerRepository.findById(id);
        if (!searchProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Product> products = searchProduct.get().getProducts();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Customer> addProductToCustomerById(Long id, Product product) {
        Optional<Customer> searchProduct = customerRepository.findById(id);
        if (!searchProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Customer customer = searchProduct.get();
        customer.getProducts().add(product);
        customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }
}
