package com.rasel.product_boots.service;

import com.rasel.product_boots.entity.Product;
import com.rasel.product_boots.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //post
    public Product saveAll(Product product) {
        Product save = productRepository.save(product);
        return save;
    }

    //get
    public List<Product> getAll() {
        List<Product> find = productRepository.findAll();
        return find;
    }


    public void deleteAll(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findProductId(Long id) {
        return productRepository.findById(id);
    }


//    public Product updateAll(Long id, Product product) {
//        product.setId(id);
//        productRepository.
//    }
}
