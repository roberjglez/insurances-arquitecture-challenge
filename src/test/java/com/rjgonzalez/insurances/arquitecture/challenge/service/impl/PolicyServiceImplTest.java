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
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ClientEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.PolicyEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ProductEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.repository.PolicyRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.PolicyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyServiceImplTest {

	@MockBean
	PolicyRepository policyRepository;

	@Autowired
	private PolicyService policyService;

	@Autowired
	ModelMapper modelMapper;

	private PolicyRQDTO policyRQDTO;

	private ClientRQDTO clientRQDTO;

	private ProductRQDTO productRQDTO;

	private PolicyEntity policyRSEntity;

	private ClientEntity clientRSEntity;

	private ProductEntity productRSEntity;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		clientRQDTO = new ClientRQDTO();
		clientRQDTO.setIdClient(1L);
		clientRQDTO.setName("Sopra");
		clientRQDTO.setSurname("Steria");
		clientRQDTO.setPhoneNumber("612345678");

		productRQDTO = new ProductRQDTO();
		productRQDTO.setIdProduct(1L);
		productRQDTO.setType("Vehicle insurance");
		productRQDTO.setPrice(240.65);

		policyRQDTO = new PolicyRQDTO();
		policyRQDTO.setType("Vehicle insurance");
		policyRQDTO.setHiringDate("2018");
		policyRQDTO.setPrice(440.72);
		policyRQDTO.setClient(clientRQDTO);
		policyRQDTO.setProduct(productRQDTO);

		clientRSEntity = new ClientEntity();

		productRSEntity = new ProductEntity();

		policyRSEntity = new PolicyEntity();
		policyRSEntity.setIdPolicy(1L);
		policyRSEntity.setType("Vehicle insurance");
		policyRSEntity.setHiringDate("2018");
		policyRSEntity.setPrice(440.72);
		policyRSEntity.setClient(clientRSEntity);
		policyRSEntity.setProduct(productRSEntity);

	}

	@Test
	public void createPolicyReturns201Test() {

		PolicyEntity policyRQEntity = modelMapper.map(policyRQDTO, PolicyEntity.class);
		assertNotNull(policyRQEntity);

		Mockito.when(policyRepository.save(Mockito.any(PolicyEntity.class))).thenReturn(policyRSEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(policyRSEntity.getIdPolicy()).toUri();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		ResponseEntity<Void> policyResponse = policyService.createPolicy(policyRQDTO);
		assertNotNull(policyResponse);
		assertEquals(HttpStatus.CREATED, policyResponse.getStatusCode());

		assertNotNull(policyResponse.getHeaders());
		assertEquals(responseHeaders, policyResponse.getHeaders());

	}

	@Test
	public void getPolicyReturns200Test() {

		Optional<PolicyEntity> entityResponse = Optional.of(policyRSEntity);
		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(entityResponse);

		policyRQDTO.setIdPolicy(1L);

		ResponseEntity<PolicyRSDTO> policyResponse = policyService.getPolicy(policyRQDTO.getIdPolicy());
		assertNotNull(policyResponse);
		assertEquals(policyResponse.getStatusCode(), HttpStatus.OK);

		if (entityResponse.isPresent()) {

			PolicyRSDTO policyResponseDTO = modelMapper.map(entityResponse.get(), PolicyRSDTO.class);
			assertNotNull(policyResponseDTO);

		}

	}

	@Test
	public void getPolicyReturns204Test() {

		Mockito.when(policyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		policyRQDTO.setIdPolicy(300L);

		ResponseEntity<PolicyRSDTO> policyResponse = policyService.getPolicy(policyRQDTO.getIdPolicy());
		assertNotNull(policyResponse);
		assertEquals(policyResponse.getStatusCode(), HttpStatus.NO_CONTENT);

	}

	@Test
	public void getAllPoliciesReturns200Test() {

		List<PolicyEntity> policiesList = new ArrayList<PolicyEntity>();
		policiesList.add(policyRSEntity);

		Mockito.when(policyRepository.findAll()).thenReturn(policiesList);

		ResponseEntity<List<PolicyRSDTO>> policyResponse = policyService.getAllPolicies();
		assertNotNull(policyResponse);
		assertEquals(policyResponse.getStatusCode(), HttpStatus.OK);

		List<PolicyRSDTO> policiesListDTO = modelMapper.map(policiesList, new TypeToken<List<PolicyRSDTO>>() {
		}.getType());

		assertNotNull(policiesListDTO);

	}

	@Test
	public void deletePolicyReturns200Test() {

		policyRQDTO.setIdPolicy(1L);
		ResponseEntity<Void> policyResponse = policyService.deletePolicy(policyRQDTO.getIdPolicy());
		assertNotNull(policyResponse);
		assertEquals(HttpStatus.OK, policyResponse.getStatusCode());

	}

	@Test
	public void updatePolicyReturns200Test() {

		PolicyEntity policyRQEntity = modelMapper.map(policyRQDTO, PolicyEntity.class);
		assertNotNull(policyRQEntity);

		Mockito.when(policyRepository.save(Mockito.any(PolicyEntity.class))).thenReturn(policyRSEntity);

		policyRQDTO.setIdPolicy(1L);

		ResponseEntity<Void> policyResponse = policyService.updatePolicy(policyRQDTO.getIdPolicy(), policyRQDTO);
		assertNotNull(policyResponse);
		assertEquals(HttpStatus.OK, policyResponse.getStatusCode());

	}

}
