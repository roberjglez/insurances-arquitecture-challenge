package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rjgonzalez.insurances.arquitecture.challenge.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

	@MockBean
	ClientRepository clientRepository;

	@MockBean
	ModelMapper modelMapper;

	@Before
	public void setUp() {

	}

	@Test
	public void addClientReturns201Test() {

	}

	@Test
	public void getClientReturns200Test() {

	}

	@Test
	public void getAllClientsReturns200Test() {

	}

	@Test
	public void deleteClientReturns200Test() {

	}

	public void updateClientReturns200Test() {

	}

}
