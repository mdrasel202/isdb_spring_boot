package com.rasel.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rasel.product.model.Products;
import com.rasel.product.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// post
	public Products saveAll(Products products) {
		int save = productRepository.save(products);
		return getProId(save);
	}

	// get
	public List<Products> getAllPro() {
		List<Products> getProduct = productRepository.findAll();
		return getProduct;
	}

	// get id
	public Products getProId(int id) {
		Optional<Products> byId = productRepository.findById(id);
		return byId.get();
	}

	// delete
	public void deleteById(int id) {
		productRepository.deleteById(id);
	}

	// update
	public Products updateEmp(int id, Products products) {
		products.setId(id);
		productRepository.update(products);
		return getProId(id);
	}

}
