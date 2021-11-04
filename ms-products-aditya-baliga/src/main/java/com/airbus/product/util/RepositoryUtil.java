package com.airbus.product.util;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.airbus.product.entity.ProductEntity;
import com.airbus.product.service.repository.ProductRepository;

@Component
public class RepositoryUtil {

	public int getRepositorySize(final ProductRepository productRepository) {
		int size = Math.toIntExact(productRepository.count());
		if (size <= 0) {
			return 1;
		}
		return size;
	}

	public Page<ProductEntity> getPage(final PageRequest pageRequest, final ProductRepository productRepository,
			final String categoryName) {
		Page<ProductEntity> page = null;
		if (StringUtils.isNotEmpty(categoryName)) {
			page = productRepository.findAllByCategory(categoryName, pageRequest);
		} else {
			page = productRepository.findAll(pageRequest);
		}
		return page;

	}

	public PageRequest getPageRequest(@Min(1) @Valid Integer count) {
		if (count != null && count > 0) {
			return PageRequest.of(0, count);

		}
		return PageRequest.of(0, 1);
	}

}
