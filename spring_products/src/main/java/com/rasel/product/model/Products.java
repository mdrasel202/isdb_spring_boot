package com.rasel.product.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Products {
	private int id;
	private String productName;
	private double price;
	private int quatity;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate purchaseDate;
	private LocalDate sellDate;
	private double amount;

	public Products() {
	}

	public Products(int id, String productName, double price, int quatity, LocalDate purchaseDate, LocalDate sellDate,
			double amount) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.quatity = quatity;
		this.purchaseDate = purchaseDate;
		this.sellDate = sellDate;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
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
