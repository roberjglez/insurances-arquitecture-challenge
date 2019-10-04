package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ClientEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.repository.ClientRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ClientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

	@MockBean
	ClientRepository clientRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	ModelMapper modelMapper;

	private ClientRQDTO clientRQDTO;

	private ClientEntity clientRSEntity;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		clientRQDTO = new ClientRQDTO();
		clientRQDTO.setName("Sopra");
		clientRQDTO.setSurname("Steria");
		clientRQDTO.setPhoneNumber("612345678");

		clientRSEntity = new ClientEntity();
		clientRSEntity.setIdClient(1L);
		clientRSEntity.setName("Sopra");
		clientRSEntity.setSurname("Steria");
		clientRSEntity.setPhoneNumber("612345678");

	}

	@Test
	public void addClientReturns201Test() {

		ClientEntity clientRQEntity = modelMapper.map(clientRQDTO, ClientEntity.class);
		assertNotNull(clientRQEntity);

		Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientRSEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientRSEntity.getIdClient()).toUri();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		ResponseEntity<Void> clientResponse = clientService.addClient(clientRQDTO);
		assertNotNull(clientResponse);
		assertEquals(HttpStatus.CREATED, clientResponse.getStatusCode());

		assertNotNull(clientResponse.getHeaders());
		assertEquals(responseHeaders, clientResponse.getHeaders());

	}

	@Test
	public void getClientReturns200Test() {

		Optional<ClientEntity> entityResponse = Optional.of(clientRSEntity);
		Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(entityResponse);

		clientRQDTO.setIdClient(1L);

		ResponseEntity<ClientRSDTO> clientResponse = clientService.getClient(clientRQDTO.getIdClient());
		assertNotNull(clientResponse);
		assertEquals(clientResponse.getStatusCode(), HttpStatus.OK);

		if (entityResponse.isPresent()) {

			ClientRSDTO clientResponseDTO = modelMapper.map(entityResponse.get(), ClientRSDTO.class);
			assertNotNull(clientResponseDTO);

		}
	}

	@Test
	public void getClientReturns204Test() {

		Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		clientRQDTO.setIdClient(300L);

		ResponseEntity<ClientRSDTO> clientResponse = clientService.getClient(clientRQDTO.getIdClient());
		assertNotNull(clientResponse);
		assertEquals(clientResponse.getStatusCode(), HttpStatus.NO_CONTENT);

	}

	@Test
	public void getAllClientsReturns200Test() {

		List<ClientEntity> clientsList = new ArrayList<ClientEntity>();
		clientsList.add(clientRSEntity);

		Mockito.when(clientRepository.findAll()).thenReturn(clientsList);

		ResponseEntity<List<ClientRSDTO>> clientResponse = clientService.getAllClients();
		assertNotNull(clientResponse);
		assertEquals(clientResponse.getStatusCode(), HttpStatus.OK);

		List<ClientRSDTO> clientsListDTO = modelMapper.map(clientsList, new TypeToken<List<ClientRSDTO>>() {
		}.getType());

		assertNotNull(clientsListDTO);

	}

	@Test
	public void deleteClientReturns200Test() {

		clientRQDTO.setIdClient(1L);
		ResponseEntity<Void> clientResponse = clientService.deleteClient(clientRQDTO.getIdClient());
		assertNotNull(clientResponse);
		assertEquals(HttpStatus.OK, clientResponse.getStatusCode());

	}

	@Test
	public void updateClientReturns200Test() {

		ClientEntity clientRQEntity = modelMapper.map(clientRQDTO, ClientEntity.class);
		assertNotNull(clientRQEntity);

		Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientRSEntity);

		clientRQDTO.setIdClient(1L);

		ResponseEntity<ClientRSDTO> clientResponse = clientService.updateClient(clientRQDTO.getIdClient(), clientRQDTO);
		assertNotNull(clientResponse);
		assertEquals(HttpStatus.OK, clientResponse.getStatusCode());

	}

}
