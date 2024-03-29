package com.rjgonzalez.insurances.arquitecture.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;

/**
 * Client Service Interface
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
public interface ClientService {

	public ResponseEntity<Void> createClient(ClientRQDTO clientRQDTO);

	public ResponseEntity<Void> deleteClient(Long idClient);

	public ResponseEntity<ClientRSDTO> getClient(Long idClient);

	public ResponseEntity<List<ClientRSDTO>> getAllClients();

	public ResponseEntity<Void> updateClient(Long idClient, ClientRQDTO clientRQDTO);
}
