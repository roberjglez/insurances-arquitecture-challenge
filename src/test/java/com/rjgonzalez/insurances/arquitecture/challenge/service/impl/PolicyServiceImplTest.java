package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void addPolicyReturns201Test() {

	}

	@Test
	public void getPolicyReturns200Test() {

	}

	@Test
	public void getPolicyReturns204Test() {

	}

	@Test
	public void getAllPoliciesReturns200Test() {

	}

	@Test
	public void deletePolicyReturns200Test() {

	}

	@Test
	public void updatePolicyReturns200Test() {

	}

}
