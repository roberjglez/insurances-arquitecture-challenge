package com.rjgonzalez.insurances.arquitecture.challenge.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Policy Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Entity
@Table(name = "policy")
public class PolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_policy")
	private Long idPolicy;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "hiring_date", nullable = false)
	private String hiringDate;

	@Column(name = "price", nullable = false)
	private double price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_product", referencedColumnName = "id_product")
	private ProductEntity product;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client", referencedColumnName = "id_client")
	private ClientEntity client;

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
	public ProductEntity getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	/**
	 * @return the client
	 */
	public ClientEntity getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientEntity client) {
		this.client = client;
	}

}
