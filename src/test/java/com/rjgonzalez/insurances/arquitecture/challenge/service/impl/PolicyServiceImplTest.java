package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rjgonzalez.insurances.arquitecture.challenge.repository.PolicyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyServiceImplTest {

	@MockBean
	PolicyRepository policyRepository;

	@MockBean
	ModelMapper modelMapper;

	@Before
	public void setUp() {

	}

	@Test
	public void addPolicyReturns201Test() {

	}

	@Test
	public void getPolicyReturns200Test() {

	}

	@Test
	public void getAllPoliciesReturns200Test() {

	}

	@Test
	public void deletePolicyReturns200Test() {

	}

	public void updatePolicyReturns200Test() {

	}

}
