package com.rjgonzalez.insurances.arquitecture.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Policy request DTO
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyRQDTO {

	private Long idPolicy;

	private String type;

	private String hiringDate;

	private double price;

	private ProductRQDTO product;

	private ClientRQDTO client;

	/**
	 * @return the idPolicy
	 */
	public Long getIdPolicy() {
		return idPolicy;
	}

	/**
	 * @param idPolicy the idPolicy to set
	 */
	public void setIdPolicy(Long idPolicy) {
		this.idPolicy = idPolicy;
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
	 * @return the hiringDate
	 */
	public String getHiringDate() {
		return hiringDate;
	}

	/**
	 * @param hiringDate the hiringDate to set
	 */
	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
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

	/**
	 * @return the product
	 */
	public ProductRQDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductRQDTO product) {
		this.product = product;
	}

	/**
	 * @return the client
	 */
	public ClientRQDTO getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientRQDTO client) {
		this.client = client;
	}

}
