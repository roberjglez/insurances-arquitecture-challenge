package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ClientRepository clientRepository;

	@Override
	public ResponseEntity<ClientRSDTO> addClient(ClientRQDTO clientRQDTO) {

		ClientEntity clientRQEntity = modelMapper.map(clientRQDTO, ClientEntity.class);

		ClientEntity clientRSEntity = clientRepository.save(clientRQEntity);

		ClientRSDTO clientResponse = modelMapper.map(clientRSEntity, ClientRSDTO.class);

		return new ResponseEntity<>(clientResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ClientRSDTO> deleteClient(Long idClient) {

		clientRepository.deleteById(idClient);

		ClientRSDTO clientRSEntity = new ClientRSDTO();

		return new ResponseEntity<>(clientRSEntity, HttpStatus.OK);
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

}
