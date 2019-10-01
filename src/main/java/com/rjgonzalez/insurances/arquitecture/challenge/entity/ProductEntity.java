package com.rjgonzalez.insurances.arquitecture.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "price", nullable = false)
	private double price;

	public ProductEntity() {
	}

	public ProductEntity(Long idProduct, String type, double price) {
		super();
		this.idProduct = idProduct;
		this.type = type;
		this.price = price;
	}

	/**
	 * @return the idProduct
	 */
	public Long getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
