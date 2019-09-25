package com.rjgonzalez.insurances.arquitecture.challenge.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Client Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Long idClient;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "client_has_policy", joinColumns = @JoinColumn(name = "id_policy", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_client", nullable = false))
	private List<PolicyEntity> policies;

	/**
	 * @return the idClient
	 */
	public Long getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the policies
	 */
	public List<PolicyEntity> getPolicies() {
		return policies;
	}

	/**
	 * @param policies the policies to set
	 */
	public void setPolicies(List<PolicyEntity> policies) {
		this.policies = policies;
	}

}
