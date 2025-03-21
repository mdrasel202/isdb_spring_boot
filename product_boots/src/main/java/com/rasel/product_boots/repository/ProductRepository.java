package com.rasel.product_boots.repository;

import com.rasel.product_boots.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
