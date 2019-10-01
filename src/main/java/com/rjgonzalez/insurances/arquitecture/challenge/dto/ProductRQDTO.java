package com.rjgonzalez.insurances.arquitecture.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Product request DTO
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRQDTO {

	private Long idProduct;

	private String type;

	private double price;

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
