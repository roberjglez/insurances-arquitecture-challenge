package com.rjgonzalez.insurances.arquitecture.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Policy Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Entity
@Table(name = "policy")

@ApiModel(description = "All details about the policy. ")
public class PolicyEntity {

	@Id
	@ApiModelProperty(notes = "The database generated policy ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_policy")
	private Long idPolicy;

	@ApiModelProperty(notes = "The policy type")
	@Column(name = "type", nullable = false)
	private String type;

	@ApiModelProperty(notes = "The policy hiring date")
	@Column(name = "hiring_date", nullable = false)
	private String hiringDate;

	@ApiModelProperty(notes = "The policy price")
	@Column(name = "price", nullable = false)
	private double price;

	@ManyToOne
	@ApiModelProperty(notes = "Id product hired in the policy")
	@JoinColumn(name = "product", referencedColumnName = "id_product")
	private ProductEntity product;

	@ManyToOne
	@ApiModelProperty(notes = "Id client that hired the policy")
	@JoinColumn(name = "client", referencedColumnName = "id_client")
	private ClientEntity client;

	public PolicyEntity() {
	}

	public PolicyEntity(Long idPolicy, String type, String hiringDate, double price, ProductEntity product,
			ClientEntity client) {
		super();
		this.idPolicy = idPolicy;
		this.type = type;
		this.hiringDate = hiringDate;
		this.price = price;
		this.product = product;
		this.client = client;
	}

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
	 * @return the clients
	 */
	public ClientEntity getClient() {
		return client;
	}

	/**
	 * @param clients the clients to set
	 */
	public void setClient(ClientEntity client) {
		this.client = client;
	}

}
