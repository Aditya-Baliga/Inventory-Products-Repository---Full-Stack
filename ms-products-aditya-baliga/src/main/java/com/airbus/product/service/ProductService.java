package com.airbus.product.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;

import com.airbus.product.model.Product;

@Service
public interface ProductService {

	List<Product> getAllProductsByCategory(String categoryName, @Min(1) @Valid Integer count);

	List<Product> getAllProducts(@Min(1) @Valid Integer count);

	Product createProduct(@Valid Product product);

	Product getProduct(UUID productId);

	Product updateProduct(UUID productId, @Valid Product product);

	void deleteProduct(UUID productId);
	

	

}
