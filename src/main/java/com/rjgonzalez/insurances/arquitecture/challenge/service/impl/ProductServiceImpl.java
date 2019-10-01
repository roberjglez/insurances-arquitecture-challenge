package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.ProductEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.repository.ProductRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ProductService;

/**
 * Product Service Implementation
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<ProductRSDTO> addProduct(ProductRQDTO productRQDTO) {

		ProductEntity productRQEntity = modelMapper.map(productRQDTO, ProductEntity.class);

		ProductEntity policyRSEntity = productRepository.save(productRQEntity);

		ProductRSDTO productResponse = modelMapper.map(policyRSEntity, ProductRSDTO.class);

		return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductRSDTO> deleteProduct(Long idProduct) {

		productRepository.deleteById(idProduct);

		ProductRSDTO productRSEntity = new ProductRSDTO();

		return new ResponseEntity<>(productRSEntity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductRSDTO> getProduct(Long idProduct) {

		Optional<ProductEntity> productEntity = productRepository.findById(idProduct);

		if (productEntity.isPresent()) {

			ProductRSDTO productResponse = modelMapper.map(productEntity.get(), ProductRSDTO.class);

			return new ResponseEntity<>(productResponse, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<List<ProductRSDTO>> getAllProducts() {

		List<ProductEntity> productsListEntity = productRepository.findAll();

		List<ProductRSDTO> productsListDTO = modelMapper.map(productsListEntity, new TypeToken<List<ProductRSDTO>>() {
		}.getType());

		return new ResponseEntity<>(productsListDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductRSDTO> updateProduct(Long idProduct, ProductRQDTO productRQDTO) {

		productRQDTO.setIdProduct(idProduct);

		ProductEntity productRQEntity = modelMapper.map(productRQDTO, ProductEntity.class);

		ProductEntity productRSEntity = productRepository.save(productRQEntity);

		ProductRSDTO productResponse = modelMapper.map(productRSEntity, ProductRSDTO.class);

		return new ResponseEntity<>(productResponse, HttpStatus.OK);

	}

}
