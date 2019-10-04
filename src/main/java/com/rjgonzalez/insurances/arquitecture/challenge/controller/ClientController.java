package com.rjgonzalez.insurances.arquitecture.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to manage clients
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@Api(value = "API Rest for insurance clients.")
@RequestMapping(value = "/api-challenge/clients")
public class ClientController {

	ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	/**
	 * Endpoint to add new clients
	 * 
	 * @param clientRQDTO -> client with all the information
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add new client of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Client created succesfully") })
	public ResponseEntity<Void> createClient(
			@ApiParam(value = "New client object to store in database table", required = true) @RequestBody ClientRQDTO clientRQDTO) {

		return clientService.createClient(clientRQDTO);

	}

	/**
	 * Endpoint to get a client by ID
	 * 
	 * @param idClient -> id client that we want to get
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@GetMapping(path = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieve a client of the insurance company.", response = ClientRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client returned succesfully"),
			@ApiResponse(code = 204, message = "Client not found") })
	public ResponseEntity<ClientRSDTO> getClient(
			@ApiParam(value = "Client id that we want to get from database table", required = true) @PathVariable Long idClient) {

		return clientService.getClient(idClient);

	}

	/**
	 * Endpoint to delete a client by ID
	 * 
	 * @param idClient -> id client that we want to delete
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@DeleteMapping(path = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a client of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client deleted succesfully") })
	public ResponseEntity<Void> deleteClient(
			@ApiParam(value = "Client id that we want to delete in database table", required = true) @PathVariable Long idClient) {

		return clientService.deleteClient(idClient);

	}

	/**
	 * Endpoint to get all clients
	 * 
	 * @return ResponseEntity<List<ClientRSDTO>> -> clients list and http status
	 *
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieve the list of all clients of the insurance company.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clients list found succesfully") })
	public ResponseEntity<List<ClientRSDTO>> getAllClients() {

		return clientService.getAllClients();

	}

	/**
	 * Endpoint to update a specific client
	 * 
	 * @param idClient    -> id client that we want to update
	 * @param clientRQDTO -> client with all the information
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@PutMapping(path = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a client of the insurance company.", response = ClientRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client updated succesfully") })
	public ResponseEntity<Void> updateClient(
			@ApiParam(value = "Client id that we want to update in database table", required = true) @PathVariable Long idClient,
			@ApiParam(value = "Client object to update in database table", required = true) @RequestBody ClientRQDTO clientRQDTO) {

		return clientService.updateClient(idClient, clientRQDTO);

	}

}
