package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ClientEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.repository.ClientRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ClientService;

/**
 * Client Service Implementation
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	ModelMapper modelMapper;
	ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<Void> createClient(ClientRQDTO clientRQDTO) {

		ClientEntity clientRQEntity = modelMapper.map(clientRQDTO, ClientEntity.class);

		ClientEntity clientRSEntity = clientRepository.save(clientRQEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientRSEntity.getIdClient()).toUri();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<Void> deleteClient(Long idClient) {

		clientRepository.deleteById(idClient);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientRSDTO> getClient(Long idClient) {

		Optional<ClientEntity> clientEntity = clientRepository.findById(idClient);

		if (clientEntity.isPresent()) {

			ClientRSDTO clientResponse = modelMapper.map(clientEntity.get(), ClientRSDTO.class);

			return new ResponseEntity<>(clientResponse, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<List<ClientRSDTO>> getAllClients() {

		List<ClientEntity> clientsListEntity = clientRepository.findAll();

		List<ClientRSDTO> clientsListDTO = modelMapper.map(clientsListEntity, new TypeToken<List<ClientRSDTO>>() {
		}.getType());

		return new ResponseEntity<>(clientsListDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> updateClient(Long idClient, ClientRQDTO clientRQDTO) {

		clientRQDTO.setIdClient(idClient);

		ClientEntity clientRQEntity = modelMapper.map(clientRQDTO, ClientEntity.class);

		ClientEntity clientRSEntity = clientRepository.save(clientRQEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientRSEntity.getIdClient()).toUri();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
	}

}
