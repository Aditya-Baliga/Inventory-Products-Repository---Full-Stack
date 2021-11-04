package com.airbus.product.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airbus.product.entity.ProductEntity;
import com.airbus.product.model.Product;
import com.airbus.product.service.exception.DuplicateResourceException;
import com.airbus.product.service.exception.GetResourceException;
import com.airbus.product.service.repository.ProductRepository;
import com.airbus.product.util.CustomMapper;

@Component
public class ProductServiceHelper {

	@Autowired
	private CustomMapper customMapper;

	@Autowired
	private ProductRepository productRepository;

	public ProductEntity getProductEntity(final UUID id) {
		if (id != null) {
			Optional<ProductEntity> oProductEntity = productRepository.findById(id);
			if (oProductEntity.isPresent()) {
				return oProductEntity.get();
			}
		}
		throw new GetResourceException("Product for the given ID not present in the database", "NOT_FOUND", "404");
	}

	public void validateProductDuplication(final Product product, final UUID productId) {
		ProductEntity productEntity = productRepository.findByName(product.getName());
		if (productEntity!=null) {
			if (productId != null && productId.equals(productEntity.getId())) {
				return;
			}
			throw new DuplicateResourceException(
					"Duplicate Error , Product with name : " + product.getName() + " already exists!");
		}
	}
}
