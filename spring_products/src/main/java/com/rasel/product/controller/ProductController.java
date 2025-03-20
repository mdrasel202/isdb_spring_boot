package com.rasel.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasel.product.model.Products;
import com.rasel.product.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// post data
	@PostMapping
	public Products createById(@RequestBody Products products) {
		Products saveId = productService.saveAll(products);
		return saveId;
	}

	// get id
	@GetMapping("/{id}")
	public Products getProId(@PathVariable("id") Long id) {
		Products getPro = productService.getProId(id);
		return getPro;
	}

	// get data
	@GetMapping
	public List<Products> getAllPro() {
		List<Products> getAll = productService.getAllPro();
		return getAll;
	}

	// delete
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		productService.deleteById(id);
	}

	// update
	@PutMapping("/{id}")
	public Products updateEmp(@PathVariable("id") Long id, @RequestBody Products products) {
		Products update = productService.updateEmp(id, products);
		return update;
	}
}
