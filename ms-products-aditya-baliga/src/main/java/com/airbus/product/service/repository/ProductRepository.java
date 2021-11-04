package com.airbus.product.service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airbus.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{
	
		
	ProductEntity findByName(@Param("name")String name);
	
	Page<ProductEntity> findAllByCategory(@Param("category") String category, Pageable page);
	
}
