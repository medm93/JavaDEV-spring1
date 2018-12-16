package com.pgs.java_dev.services;

import com.pgs.java_dev.model.Product;
import com.pgs.java_dev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<Object> createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Long id = productRepository.save(product).getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    public ResponseEntity<Product> findProductById(Long id) {
        Optional<Product> searchResult = productRepository.findById(id);
        if (!searchResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Product product = searchResult.get();
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Object> updateProductById(Long id, Product product) {
        Optional<Product> searchResult = productRepository.findById(id);
        if (!searchResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Product productInDB = searchResult.get();
        productInDB.setName(product.getName());
        productInDB.setDescription(product.getDescription());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Object> deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
