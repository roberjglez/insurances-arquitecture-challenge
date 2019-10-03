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
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

	private static final String GET_PRODUCT_PATH = "/api-challenge/products/1";
	private static final String POST_PRODUCT_PATH = "/api-challenge/products";
	private static final String GET_ALL_PRODUCTS_PATH = "/api-challenge/products";

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ProductController productController;

	@MockBean
	private ProductService productService;

	private ProductRQDTO productRQDTO;
	private ProductRSDTO productRSDTO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		productController = new ProductController(productService);

		productRQDTO = new ProductRQDTO();
		productRQDTO.setType("Vehicle insurance");
		productRQDTO.setPrice(240.65);

		productRSDTO = new ProductRSDTO();
		productRSDTO.setIdProduct(2L);
		productRSDTO.setType("Life insurance");
		productRSDTO.setPrice(210.50);

	}

	@Test
	public void addProductReturns201Test() throws Exception {

		ResponseEntity<Void> entity = new ResponseEntity<Void>(HttpStatus.CREATED);

		Mockito.when(productService.addProduct(Mockito.any(ProductRQDTO.class))).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(productRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(POST_PRODUCT_PATH)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void getProductReturns200Test() throws Exception {

		ResponseEntity<ProductRSDTO> rsEntity = new ResponseEntity<ProductRSDTO>(productRSDTO, HttpStatus.OK);

		Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(rsEntity);

		Gson gson = new Gson();
		String json = gson.toJson(productRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_PRODUCT_PATH).accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void deleteProductReturns200Test() throws Exception {

		ResponseEntity<Void> rsEntity = new ResponseEntity<Void>(HttpStatus.OK);

		Mockito.when(productService.deleteProduct(Mockito.anyLong())).thenReturn(rsEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GET_PRODUCT_PATH)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void getAllProductsReturns200Test() throws Exception {

		List<ProductRSDTO> productsList = new ArrayList<ProductRSDTO>();
		productsList.add(productRSDTO);

		ResponseEntity<List<ProductRSDTO>> entity = new ResponseEntity<List<ProductRSDTO>>(productsList, HttpStatus.OK);

		Mockito.when(productService.getAllProducts()).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(productsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_ALL_PRODUCTS_PATH)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void updateProductReturns200Test() throws Exception {

		productRQDTO.setIdProduct(1L);

		ResponseEntity<ProductRSDTO> entity = new ResponseEntity<ProductRSDTO>(productRSDTO, HttpStatus.OK);

		Mockito.when(productService.updateProduct(productRQDTO.getIdProduct(), productRQDTO)).thenReturn(entity);

		Gson gson = new Gson();
		String json = gson.toJson(productRQDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GET_PRODUCT_PATH).content(json)
				.accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

}
