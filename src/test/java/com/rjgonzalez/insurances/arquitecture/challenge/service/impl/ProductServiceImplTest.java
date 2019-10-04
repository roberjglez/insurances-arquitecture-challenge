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

import com.rjgonzalez.insurances.arquitecture.challenge.repository.ProductRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

	@MockBean
	ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	ModelMapper modelMapper;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void addProductReturns201Test() {

	}

	@Test
	public void getProductReturns200Test() {

	}

	@Test
	public void getProductReturns204Test() {

	}

	@Test
	public void getAllProductsReturns200Test() {

	}

	@Test
	public void deleteProductReturns200Test() {

	}

	@Test
	public void updateProductReturns200Test() {

	}

}
