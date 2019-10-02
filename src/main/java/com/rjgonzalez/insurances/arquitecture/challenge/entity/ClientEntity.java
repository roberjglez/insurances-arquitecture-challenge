package com.rjgonzalez.insurances.arquitecture.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Client Entity
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Entity
@Table(name = "client")
@ApiModel(description = "All details about the client. ")
public class ClientEntity {

	@Id
	@ApiModelProperty(notes = "The database generated client ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Long idClient;

	@ApiModelProperty(notes = "The client name")
	@Column(name = "name", nullable = false)
	private String name;

	@ApiModelProperty(notes = "The client surname")
	@Column(name = "surname", nullable = false)
	private String surname;

	@ApiModelProperty(notes = "The client phone number")
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	public ClientEntity() {
	}

	public ClientEntity(Long idClient, String name, String surname, String phoneNumber) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}

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

}
