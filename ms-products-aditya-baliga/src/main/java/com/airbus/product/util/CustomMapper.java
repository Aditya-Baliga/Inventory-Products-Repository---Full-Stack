package com.airbus.product.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.airbus.product.entity.ProductEntity;
import com.airbus.product.model.Product;

@Component
public class CustomMapper {
	
	public ProductEntity productToProductEntity(final Product product) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCategory(product.getCategory());
		productEntity.setDescription(product.getDescription());
		productEntity.setName(product.getName());
		productEntity.setUnits(product.getUnits());
		if(product.getId() != null) {
			productEntity.setId(UUID.fromString(product.getId()));
		}
		return productEntity;
	}

	public Product productEntityToProduct(final ProductEntity productEntity) {
		Product product = new Product();
		product.setId(productEntity.getId().toString());
		product.setCategory(productEntity.getCategory());
		product.setDescription(productEntity.getDescription());
		product.setName(productEntity.getName());
		product.setUnits(productEntity.getUnits());
		return product;
	}
}
