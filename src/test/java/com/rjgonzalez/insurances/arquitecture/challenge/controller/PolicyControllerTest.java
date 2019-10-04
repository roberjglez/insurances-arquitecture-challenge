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
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.PolicyService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PolicyController.class)
public class PolicyControllerTest {

	private static final String GET_POLICY_PATH = "/api-challenge/policies/1";
	private static final String POST_POLICY_PATH = "/api-challenge/policies";
	private static final String GET_ALL_POLICIES_PATH = "/api-challenge/policies";

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private PolicyController policyController;

	@MockBean
	private PolicyService policyService;

	private PolicyRQDTO policyRQDTO;
	private PolicyRSDTO policyRSDTO;

	private ClientRQDTO clientRQDTO;
	private ClientRSDTO clientRSDTO;

	private ProductRQDTO productRQDTO;
	private ProductRSDTO productRSDTO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		policyController = new PolicyController(policyService);

		clientRQDTO = new ClientRQDTO();
		clientRQDTO.setIdClient(1L);
		clientRQDTO.setName("Sopra");
		clientRQDTO.setSurname("Steria");
		clientRQDTO.setPhoneNumber("612345678");

		clientRSDTO = new ClientRSDTO();
		clientRSDTO.setIdClient(2L);
		clientRSDTO.setName("Juan");
		clientRSDTO.setSurname("Gonzalez");
		clientRSDTO.setPhoneNumber("652348678");

		productRQDTO = new ProductRQDTO();
		productRQDTO.setIdProduct(1L);
		productRQDTO.setType("Vehicle insurance");
		productRQDTO.setPrice(240.65);

		productRSDTO = new ProductRSDTO();
		productRSDTO.setIdProduct(2L);
		productRSDTO.setType("Life insurance");
		productRSDTO.setPrice(210.50);

		policyRQDTO = new PolicyRQDTO();
		policyRQDTO.setType("Vehicle insurance");
		policyRQDTO.setHiringDate("2018");
		policyRQDTO.setPrice(440.72);
		policyRQDTO.setClient(clientRQDTO);
		policyRQDTO.setProduct(productRQDTO);

		policyRSDTO = new PolicyRSDTO();
		policyRSDTO.setIdPolicy(2L);
		policyRSDTO.setType("Sopra");
		policyRSDTO.setHiringDate("Steria");
		policyRSDTO.setPrice(440.72);
		policyRSDTO.setClient(clientRSDTO);
		policyRSDTO.setProduct(productRSDTO);

	}

	@Test
	public void createPolicyReturns201Test() throws Exception {

		ResponseEntity<Void> entity = new ResponseEntity<Void>(HttpStatus.CREATED);

		Mockito.when(policyService.createPolicy(Mockito.any(PolicyRQDTO.class))).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(policyRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(POST_POLICY_PATH).accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void getPolicyReturns200Test() throws Exception {

		ResponseEntity<PolicyRSDTO> rsEntity = new ResponseEntity<PolicyRSDTO>(policyRSDTO, HttpStatus.OK);

		Mockito.when(policyService.getPolicy(Mockito.anyLong())).thenReturn(rsEntity);

		Gson gson = new Gson();
		String json = gson.toJson(policyRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_POLICY_PATH).accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void deletePolicyReturns200Test() throws Exception {

		ResponseEntity<Void> rsEntity = new ResponseEntity<Void>(HttpStatus.OK);

		Mockito.when(policyService.deletePolicy(Mockito.anyLong())).thenReturn(rsEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GET_POLICY_PATH)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void getAllPoliciesReturns200Test() throws Exception {

		List<PolicyRSDTO> policiesList = new ArrayList<PolicyRSDTO>();
		policiesList.add(policyRSDTO);

		ResponseEntity<List<PolicyRSDTO>> entity = new ResponseEntity<List<PolicyRSDTO>>(policiesList, HttpStatus.OK);

		Mockito.when(policyService.getAllPolicies()).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(policiesList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_ALL_POLICIES_PATH)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void updatePolicyReturns200Test() throws Exception {

		policyRQDTO.setIdPolicy(1L);

		ResponseEntity<Void> entity = new ResponseEntity<Void>(HttpStatus.OK);

		Mockito.when(policyService.updatePolicy(policyRQDTO.getIdPolicy(), policyRQDTO)).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(policyRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GET_POLICY_PATH).content(json)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

}
