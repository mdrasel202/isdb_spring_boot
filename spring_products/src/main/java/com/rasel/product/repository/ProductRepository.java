package com.rasel.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.rasel.product.model.Products;

@Repository
public class ProductRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert productInsert;

	public ProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

		this.productInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("products_spri")
				.usingGeneratedKeyColumns("id");
	}

	public int save(Products products) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("productName", products.getProductName());
		parameters.put("price", products.getPrice());
		parameters.put("quatity", products.getQuatity());
		parameters.put("purchaseDate", products.getPurchaseDate());
		parameters.put("sellDate", products.getSellDate());
		parameters.put("amount", products.getAmount());

		Number key = productInsert.executeAndReturnKey(parameters);
		return key.intValue();
	}

	public Optional<Products> findById(int id) {
		try {
			String sql = "SELECT * FROM products_spri WHERE ID = ?";
			return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductsRowMapper(), id));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public List<Products> findAll() {
		String sql = "SELECT * FROM products_spri";
		return jdbcTemplate.query(sql, new ProductsRowMapper());
	}

	public int update(Products products) {
		String sql = "UPDATE products_spri SET productName = ?, price = ?, quatity = ?, "
				+ "purchaseDate = ?, sellDate = ?, amount = ?";

		return jdbcTemplate.update(sql, products.getProductName(), products.getPrice(), products.getQuatity(),
				products.getPurchaseDate(), products.getSellDate(), products.getAmount());
	}

	public int deleteById(int id) {
		String sql = "DELETE FROM products_spri WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public boolean existsById(int id) {
		String sql = "SELECT COUNT(*) FROM products_spri WHERE id = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
		return count != null && count > 0;
	}

	public List<Products> findByName(String name) {
		String sql = "SELECT * FROM products_spri WHERE productName LIKE ?";
		return jdbcTemplate.query(sql, new ProductsRowMapper(), "%" + name + "%");
	}

	private static class ProductsRowMapper implements RowMapper<Products> {
		@Override
		public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Products(rs.getInt("id"), rs.getString("productName"), rs.getDouble("price"),
					rs.getInt("quatity"), rs.getObject("purchaseDate", LocalDate.class),
					rs.getObject("sellDate", LocalDate.class), rs.getDouble("amount"));
		}
	}

	public Products saveAndReturnEmp(Products products) {
		try (Connection connection = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO products_spri (productName, price, quatity, purchaseDate, sellDate, amount) VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			// Set the parameters
			preparedStatement.setString(1, products.getProductName());
			preparedStatement.setDouble(2, products.getPrice());
			preparedStatement.setInt(3, products.getQuatity());
			preparedStatement.setObject(6, products.getPurchaseDate());
			preparedStatement.setObject(6, products.getSellDate());
			preparedStatement.setDouble(7, products.getAmount());

			// Execute the insert
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating employee failed, no rows affected.");
			}

			// Get the generated ID
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int id = generatedKeys.getInt(1);

					// Set the ID in the employee object
					Products savedProducts = new Products(id, products.getProductName(), products.getPrice(),
							products.getQuatity(), products.getPurchaseDate(), products.getSellDate(),
							products.getAmount());

					return savedProducts;
				} else {
					throw new SQLException("Creating products failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error saving products", e);
		}
	}

}
