package com.airbus.product.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.airbus.product.model.Product;
import com.airbus.product.service.ProductService;
import com.airbus.product.service.exception.GenericException;
import com.airbus.product.service.exception.InputValidationException;
import com.airbus.product.service.exception.UpdateResourceException;
import com.airbus.product.util.InputValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@Controller
public class ProductApiController implements V1Api {
	Logger log = LoggerFactory.getLogger(ProductApiController.class);
	@Autowired
	private ProductService service;

	public ProductApiController() {

	}

	@Override
	public ResponseEntity<Product> createProduct(
			@ApiParam(value = "details of the product", required = true) @Valid @RequestBody Product product) {
		log.info("Request received to createProduct : " + product);
		String validationResult = InputValidationUtil.validateProductCreateRequest(product);

		if (StringUtils.isNotEmpty(validationResult)) {
			log.error("Failed to create the product : " + validationResult);
			throw new InputValidationException("Failed to create the product", HttpStatus.UNPROCESSABLE_ENTITY.value(),
					validationResult);
		}
		Product createdProduct = service.createProduct(product);

		log.info("Product entry added successfully : " + product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> deleteProduct(
			@ApiParam(value = "Unique ID of the product", required = true) @PathVariable("productId") UUID productId) {
		log.info("Request received to deleteProduct : " + productId);
		service.deleteProduct(productId);
		log.info("Product entry deleted successfully : " + productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts(@Min(1) @Valid final Integer count) {
		log.info("Request received to getAllProducts: ");
		String validationResult = InputValidationUtil.validateCount(count);
		if (StringUtils.isNotEmpty(validationResult)) {
			log.error("Failed to getAllProducts : " + validationResult);
			throw new InputValidationException("Failed to get all products", HttpStatus.BAD_REQUEST.value(),
					validationResult);
		}
		List<Product> allProducts = service.getAllProducts(count);
		log.info("All products fetched successfully : products are :  " + allProducts.toString());
		return new ResponseEntity<>(allProducts, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProductsByCategory(
			@ApiParam(value = "category name", required = true) @PathVariable("categoryName") String categoryName,
			@Min(1) @ApiParam(value = "number of products in the response will be less than or equal to value provided for count", allowableValues = "") @Valid @RequestParam(value = "count", required = false) Integer count) {
		log.info("Request received to getAllProductsByCategory : category name is : " + categoryName);
		String validationResult = InputValidationUtil.validateCount(count)
				+ InputValidationUtil.validateCategoryName(categoryName);
		if (StringUtils.isNotEmpty(validationResult)) {
			log.error("Failed to getAllProductsByCategory : " + validationResult);
			throw new InputValidationException("Failed to get all products", HttpStatus.BAD_REQUEST.value(),
					validationResult);
		}
		List<Product> allProductsOfGivenCategory = service.getAllProductsByCategory(categoryName, count);
		log.info("All productsByCategory fetched successfully : category name is: " + categoryName + "prodducts are : "
				+ allProductsOfGivenCategory.toString());
		return new ResponseEntity<>(allProductsOfGivenCategory, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> getProduct(
			@ApiParam(value = "Unique ID of the product", required = true) @PathVariable("productId") UUID productId) {
		log.info("Request received to getProduct : " + productId);
		Product product = service.getProduct(productId);
		if (product != null) {
			log.info("Product fetched successfully : " + productId + "product is : " + product.toString());
			return new ResponseEntity<>(product, HttpStatus.OK);
		}
		log.info("Product getProduct not found : " + productId);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Product> updateProduct(
			@ApiParam(value = "details of the product", required = true) @Valid @RequestBody Product product,
			@ApiParam(value = "Unique ID of the product", required = true) @PathVariable("productId") UUID productId) {

		log.info("Request received to updateProduct : product id is: " + productId + " product is : " + product);
		String validationResult = InputValidationUtil.validateProductUpdateRequest(product);
		if (StringUtils.isNotEmpty(validationResult)) {
			log.error("Failed to updateProduct : " + validationResult);
			throw new InputValidationException("Failed to update product", HttpStatus.BAD_REQUEST.value(),
					validationResult);
		}
		Product updatedProduct = service.updateProduct(productId, product);
		if (updatedProduct != null) {
			log.info("Product updated successfully : " + productId + " product is : " + product.toString());
			return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		}
		log.info("Product updateProduct not found : " + productId);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
