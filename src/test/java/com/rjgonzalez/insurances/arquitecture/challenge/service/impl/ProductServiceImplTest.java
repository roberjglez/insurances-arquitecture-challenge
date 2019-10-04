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

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ProductEntity;
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

	private ProductRQDTO productRQDTO;

	private ProductEntity productRSEntity;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);

		productRQDTO = new ProductRQDTO();
		productRQDTO.setIdProduct(1L);
		productRQDTO.setType("Vehicle insurance");
		productRQDTO.setPrice(240.65);

		productRSEntity = new ProductEntity();
		productRSEntity.setIdProduct(1L);
		productRSEntity.setType("Vehicle insurance");
		productRSEntity.setPrice(240.65);

	}

	@Test
	public void createProductReturns201Test() {

		ProductEntity productRQEntity = modelMapper.map(productRQDTO, ProductEntity.class);
		assertNotNull(productRQEntity);

		Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(productRSEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productRSEntity.getIdProduct()).toUri();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(location);

		ResponseEntity<Void> productResponse = productService.createProduct(productRQDTO);
		assertNotNull(productResponse);
		assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());

		assertNotNull(productResponse.getHeaders());
		assertEquals(responseHeaders, productResponse.getHeaders());

	}

	@Test
	public void getProductReturns200Test() {

		Optional<ProductEntity> entityResponse = Optional.of(productRSEntity);
		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(entityResponse);

		productRQDTO.setIdProduct(1L);

		ResponseEntity<ProductRSDTO> productResponse = productService.getProduct(productRQDTO.getIdProduct());
		assertNotNull(productResponse);
		assertEquals(productResponse.getStatusCode(), HttpStatus.OK);

		if (entityResponse.isPresent()) {

			ProductRSDTO productResponseDTO = modelMapper.map(entityResponse.get(), ProductRSDTO.class);
			assertNotNull(productResponseDTO);

		}

	}

	@Test
	public void getProductReturns204Test() {

		Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		productRQDTO.setIdProduct(300L);

		ResponseEntity<ProductRSDTO> productResponse = productService.getProduct(productRQDTO.getIdProduct());
		assertNotNull(productResponse);
		assertEquals(productResponse.getStatusCode(), HttpStatus.NO_CONTENT);

	}

	@Test
	public void getAllProductsReturns200Test() {

		List<ProductEntity> productsList = new ArrayList<ProductEntity>();
		productsList.add(productRSEntity);

		Mockito.when(productRepository.findAll()).thenReturn(productsList);

		ResponseEntity<List<ProductRSDTO>> productResponse = productService.getAllProducts();
		assertNotNull(productResponse);
		assertEquals(productResponse.getStatusCode(), HttpStatus.OK);

		List<ProductRSDTO> productsListDTO = modelMapper.map(productsList, new TypeToken<List<ProductRSDTO>>() {
		}.getType());

		assertNotNull(productsListDTO);

	}

	@Test
	public void deleteProductReturns200Test() {

		productRQDTO.setIdProduct(1L);
		ResponseEntity<Void> productResponse = productService.deleteProduct(productRQDTO.getIdProduct());
		assertNotNull(productResponse);
		assertEquals(HttpStatus.OK, productResponse.getStatusCode());

	}

	@Test
	public void updateProductReturns200Test() {

		ProductEntity productRQEntity = modelMapper.map(productRQDTO, ProductEntity.class);
		assertNotNull(productRQEntity);

		Mockito.when(productRepository.save(Mockito.any(ProductEntity.class))).thenReturn(productRSEntity);

		productRQDTO.setIdProduct(1L);

		ResponseEntity<Void> productResponse = productService.updateProduct(productRQDTO.getIdProduct(), productRQDTO);
		assertNotNull(productResponse);
		assertEquals(HttpStatus.OK, productResponse.getStatusCode());

	}

}
