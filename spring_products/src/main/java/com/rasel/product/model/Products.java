package com.rasel.product.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Products {
	private Long id;
	private String productName;
	private double price;
	private Long quatity;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate purchaseDate;
	private LocalDate sellDate;
	private double amount;

	public Products() {
	}

	public Products(Long id, String productName, double price, Long quatity, LocalDate purchaseDate, LocalDate sellDate,
			double amount) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quatity = quatity;
		this.purchaseDate = purchaseDate;
		this.sellDate = sellDate;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getQuatity() {
		return quatity;
	}

	public void setQuatity(Long quatity) {
		this.quatity = quatity;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getSellDate() {
		return sellDate;
	}

	public void setSellDate(LocalDate sellDate) {
		this.sellDate = sellDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
