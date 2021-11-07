package com.airbus.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.airbus.product.entity.ProductEntity;
import com.airbus.product.model.Product;
import com.airbus.product.service.ProductService;
import com.airbus.product.service.exception.CreateResourceException;
import com.airbus.product.service.exception.DeleteResourceException;
import com.airbus.product.service.exception.DuplicateResourceException;
import com.airbus.product.service.exception.GenericException;
import com.airbus.product.service.exception.ResourceNotFoundException;
import com.airbus.product.service.exception.UpdateResourceException;
import com.airbus.product.service.repository.ProductRepository;
import com.airbus.product.util.CustomMapper;
import com.airbus.product.util.InputValidationUtil;
import com.airbus.product.util.RepositoryUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomMapper customMapper;

	@Autowired
	private ProductServiceHelper productServiceHelper;

	@Autowired
	private RepositoryUtil repositoryUtil;

	@Override
	public List<Product> getAllProductsByCategory(String categoryName, @Min(1) @Valid Integer count) {
		try {
			List<ProductEntity> allProducts = new ArrayList<>();
			List<Product> products = new ArrayList<Product>();
			PageRequest pageRequest = null;
			if (count != null && StringUtils.isEmpty(InputValidationUtil.validateCount(count))) {
				pageRequest = repositoryUtil.getPageRequest(count);
			} else {
				pageRequest = repositoryUtil.getPageRequest(repositoryUtil.getRepositorySize(productRepository));
			}

			Page<ProductEntity> page = repositoryUtil.getPage(pageRequest, productRepository, categoryName);

			if (page != null) {
				allProducts.addAll(page.getContent());
			}

			if (!allProducts.isEmpty()) {
				allProducts.forEach(pe -> products.add(customMapper.productEntityToProduct(pe)));
			}

			return products;
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(),
					"Failed to get All Products By Category : " + categoryName, "36912", e);
		}
	}

	@Override
	public List<Product> getAllProducts(@Min(1) @Valid Integer count) {
		try {
			List<ProductEntity> allProducts = new ArrayList<>();
			List<Product> products = new ArrayList<Product>();
			PageRequest pageRequest = null;
			if (count != null && StringUtils.isEmpty(InputValidationUtil.validateCount(count))) {
				pageRequest = repositoryUtil.getPageRequest(count);
			} else {
				pageRequest = repositoryUtil.getPageRequest(repositoryUtil.getRepositorySize(productRepository));
			}

			Page<ProductEntity> page = repositoryUtil.getPage(pageRequest, productRepository, null);

			if (page != null) {
				allProducts.addAll(page.getContent());
			}

			if (!allProducts.isEmpty()) {
				allProducts.forEach(pe -> products.add(customMapper.productEntityToProduct(pe)));
			}

			return products;
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(), "Failed to get All Products : ",
					"481216", e);
		}
	}

	@Override
	public Product createProduct(@Valid Product product) {

		try {
			productServiceHelper.validateProductDuplication(product, null);

			ProductEntity productEntity = productRepository.save(customMapper.productToProductEntity(product));

			return customMapper.productEntityToProduct(productEntity);
		} catch (DuplicateResourceException | CreateResourceException e) {
			throw new CreateResourceException(e.getMessage(), "SERVER_ERROR", "Product creation failed", e);
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(),
					"Failed to create product with id : " + product.getId(), "45678", e);
		}
	}

	@Override
	public Product getProduct(UUID productId) {
		try {
			ProductEntity productEntity = productServiceHelper.getProductEntity(productId);
			return customMapper.productEntityToProduct(productEntity);
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(), "Failed to get Product : " + productId,
					"987654", e);
		}
	}

	@Override
	public Product updateProduct(UUID productId, @Valid Product product) {
		try {
			productServiceHelper.validateProductDuplication(product, productId);
			Optional<ProductEntity> oProductEntity = productRepository.findById(productId);
			if (!oProductEntity.isPresent()) {
				ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(
						"Product with given id not found");
				throw new UpdateResourceException("Failed to update product. Product with given ID not found",
						"Product not found", "Product wih given id not present ", resourceNotFoundException);
			}
			ProductEntity productEntity = oProductEntity.get();
			productEntity = customMapper.productToProductEntity(product);
			productEntity.setId(productId);

			return customMapper.productEntityToProduct(productRepository.save(productEntity));

		} catch (UpdateResourceException | IllegalArgumentException e) {
			throw new UpdateResourceException(e.getMessage(), "SERVER_ERROR", "Product deletion failed", e);
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(),
					"Failed to update product with id : " + productId, "12345", e);
		}
	}

	@Override
	public void deleteProduct(UUID productId) {
		try {
			Optional<ProductEntity> oProductEntity = productRepository.findById(productId);
			if (!oProductEntity.isPresent()) {
				ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(
						"Product with given id not found");
				throw new DeleteResourceException("Failed to delete product. Product with given ID not found",
						"Product not found", "Product wih given id not present ", resourceNotFoundException);
			}
			ProductEntity productEntity = oProductEntity.get();

			productRepository.delete(productEntity);

		} catch (DeleteResourceException | IllegalArgumentException e) {
			throw new DeleteResourceException(e.getMessage(), "SERVER_ERROR", "Product deletion failed", e);
		} catch (Exception e) {
			throw new GenericException("Product service impl", e.getMessage(),
					"Failed to delete product with id : " + productId, "24686", e);
		}
	}
}
