package com.airbus.product.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.airbus.product.model.Product;

public final class InputValidationUtil {
	
	public static String validateCount(final Integer count) {
		if(count !=null && count <= 0) {
			return "Count attribute should be greater than zero \n";
		}
		return StringUtils.EMPTY;
	}

	public static boolean isValidString(final String input, final int sizeLimit, final boolean required) {
		if (required && StringUtils.isBlank(input) || input != null && input.length() > sizeLimit) {
			return false;
		}
		return true;
	}
	
	public static String validateCategoryName(final String input) {
		if(isValidString(input, 256, true)) {
			return StringUtils.EMPTY;
		}
		return "Category is either null or having size greater than 256\n";
	}

	public static String validateProductCreateRequest(final Product product) {
		StringBuilder validationResult = new StringBuilder();
		if (product == null) {
			return "\n Empty product is invalid input";
		}
		validationResult.append(validateProductProperties(product));
		return validationResult.toString();

	}
	
	public static String validateProductProperties(final Product product) {
		StringBuilder validationResult = new StringBuilder();
		if (!isValidString(product.getName(), 256, true)) {
			validationResult.append("\n length of name of product should be less than 256 characters");
		}

		if (!isValidString(product.getDescription(), 1000, true)) {
			validationResult.append("\n length of name of product should be less than 1000 characters");
		}
		
		if (!isValidString(product.getCategory(), 256, true)) {
			validationResult.append("\n length of category of product should be less than 256 characters");
		}

		if (product.getUnits() == null || product.getUnits() <= -1) {
			validationResult.append("\n product units is less than zero");
		}

		if (product.getId() != null) {
			validationResult.append("\n ID is autogenerated, please avoid adding it");
		}
		
		return validationResult.toString();
	}
	
	public static String validateProductUpdateRequest(final Product product) {
		StringBuilder validationResult = new StringBuilder();
		if(product == null) {
			return "\n Empty product update request sent";
		}
		validationResult.append(validateProductProperties(product));
		return validationResult.toString();
	}
}
