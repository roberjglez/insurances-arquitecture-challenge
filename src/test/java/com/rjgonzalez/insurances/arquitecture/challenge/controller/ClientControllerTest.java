package com.rjgonzalez.insurances.arquitecture.challenge.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ClientRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ClientService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class)
public class ClientControllerTest {

	private static final String GET_CLIENT_PATH = "/api-challenge/clients/1";
	private static final String POST_CLIENT_PATH = "/api-challenge/clients";
	private static final String GET_ALL_CLIENTS_PATH = "/api-challenge/clients";

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ClientController clientController;

	@MockBean
	private ClientService clientService;

	private ClientRQDTO clientRQDTO;
	private ClientRSDTO clientRSDTO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		clientController = new ClientController(clientService);

		clientRQDTO = new ClientRQDTO();
		clientRQDTO.setName("Sopra");
		clientRQDTO.setSurname("Steria");
		clientRQDTO.setPhoneNumber("612345678");

		clientRSDTO = new ClientRSDTO();
		clientRSDTO.setIdClient(1L);
		clientRSDTO.setName("Sopra");
		clientRSDTO.setSurname("Steria");
		clientRSDTO.setPhoneNumber("612345678");
	}

	@Test
	public void addClientReturns201Test() throws Exception {

		ResponseEntity<Void> entity = new ResponseEntity<Void>(HttpStatus.CREATED);

		Mockito.when(clientService.addClient(Mockito.any(ClientRQDTO.class))).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(clientRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(POST_CLIENT_PATH).accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void getClientReturns200Test() throws Exception {

		ResponseEntity<ClientRSDTO> rsEntity = new ResponseEntity<ClientRSDTO>(clientRSDTO, HttpStatus.OK);

		Mockito.when(clientService.getClient(Mockito.anyLong())).thenReturn(rsEntity);

		Gson gson = new Gson();
		String json = gson.toJson(clientRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_CLIENT_PATH).accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void deleteClientReturns200Test() throws Exception {

		ResponseEntity<Void> rsEntity = new ResponseEntity<Void>(HttpStatus.OK);

		Mockito.when(clientService.deleteClient(Mockito.anyLong())).thenReturn(rsEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GET_CLIENT_PATH)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void getAllClientsReturns200Test() throws Exception {

		List<ClientRSDTO> clientsList = new ArrayList<ClientRSDTO>();
		clientsList.add(clientRSDTO);

		ResponseEntity<List<ClientRSDTO>> entity = new ResponseEntity<List<ClientRSDTO>>(clientsList, HttpStatus.OK);

		Mockito.when(clientService.getAllClients()).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(clientsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_ALL_CLIENTS_PATH)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void updateClientReturns200Test() throws Exception {

		clientRQDTO.setIdClient(1L);

		ResponseEntity<ClientRSDTO> entity = new ResponseEntity<ClientRSDTO>(clientRSDTO, HttpStatus.OK);

		Mockito.when(clientService.updateClient(clientRQDTO.getIdClient(), clientRQDTO)).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(clientRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GET_CLIENT_PATH).content(json)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

}
