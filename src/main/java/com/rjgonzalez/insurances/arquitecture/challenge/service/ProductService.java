package com.rjgonzalez.insurances.arquitecture.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;

/**
 * Product Service Interface
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
public interface ProductService {

	public ResponseEntity<ProductRSDTO> addProduct(ProductRQDTO productRQDTO);

	public ResponseEntity<ProductRSDTO> deleteProduct(Long idProduct);

	public ResponseEntity<ProductRSDTO> getProduct(Long idProduct);

	public ResponseEntity<List<ProductRSDTO>> getAllProducts();
}
