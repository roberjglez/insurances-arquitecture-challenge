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
}
