package com.rjgonzalez.insurances.arquitecture.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller to manage clients
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@Api(value = "API Rest for insurance clients.")
@RequestMapping({ "api-challenge/client" })
public class ClientController {

	@Autowired
	ClientService clientService;

	/**
	 * Endpoint to add new clients
	 * 
	 * @param clientRQDTO -> client with all the information
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@PostMapping(path = "/addClient", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add new client of the insurance company.")
	public ResponseEntity<ClientRSDTO> addClient(@RequestBody ClientRQDTO clientRQDTO) {

		return clientService.addClient(clientRQDTO);

	}

	/**
	 * Endpoint to get a client by ID
	 * 
	 * @param idClient -> id client that we want to get
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@GetMapping(path = "/getClient/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve a client of the insurance company.")
	public ResponseEntity<ClientRSDTO> getClient(@PathVariable Long idClient) {

		return clientService.getClient(idClient);

	}

	/**
	 * Endpoint to delete a client by ID
	 * 
	 * @param idClient -> id client that we want to delete
	 * @return ResponseEntity<ClientRSDTO> -> client and http status
	 *
	 */
	@DeleteMapping(path = "/deleteClient/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Delete a client of the insurance company.")
	public ResponseEntity<ClientRSDTO> deleteClient(@PathVariable Long idClient) {

		return clientService.deleteClient(idClient);

	}

	/**
	 * Endpoint to get all clients
	 * 
	 * @return ResponseEntity<List<ClientRSDTO>> -> clients list and http status
	 *
	 */
	@GetMapping(path = "/getAllClients", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve the list of all clients of the insurance company.")
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
	@PatchMapping(path = "/updateClient/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a client of the insurance company.")
	public ResponseEntity<ClientRSDTO> updateClient(@PathVariable Long idClient, @RequestBody ClientRQDTO clientRQDTO) {

		return clientService.updateClient(idClient, clientRQDTO);

	}

}
