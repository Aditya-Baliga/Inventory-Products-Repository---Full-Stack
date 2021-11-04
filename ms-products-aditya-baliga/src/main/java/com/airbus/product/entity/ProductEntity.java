package com.airbus.product.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID")
	private UUID id;

	@Column(name = "CATEGORY", length = 256, nullable = false)
	private String category;
	
	@Column(name = "NAME", length = 256, nullable = false, unique = true)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 1000)
	private String description;
	
	@Column(name = "UNITS", nullable = false )
	@Min(value = 0, message = "Units must always be greater or equal to zero")
	private Long units;
	
	public ProductEntity() {
	}

	public ProductEntity(UUID id, String category, String name, String description, Long units) {
		this.id = id;
		this.category = category;
		this.name = name;
		this.description = description;
		this.units = units;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

}
