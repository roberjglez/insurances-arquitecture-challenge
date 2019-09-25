package com.rjgonzalez.insurances.arquitecture.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ProductService;

/**
 * Controller to manage products
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@RequestMapping({ "api-challenge/product" })
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Endpoint to add new products
	 * 
	 * @param ProductRQDTO -> product with all the information
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@PostMapping(path = "/addProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductRSDTO> addProduct(@RequestBody ProductRQDTO productRQDTO) {

		return productService.addProduct(productRQDTO);

	}

	/**
	 * Endpoint to get a product by ID
	 * 
	 * @param idProduct -> id product that we want to get
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@GetMapping(path = "/getProduct/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductRSDTO> getProduct(@PathVariable Long idProduct) {

		return productService.getProduct(idProduct);

	}

	/**
	 * Endpoint to delete a product by ID
	 * 
	 * @param idProduct -> id product that we want to delete
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@DeleteMapping(path = "/deleteProduct/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ProductRSDTO> deleteProduct(@PathVariable Long idProduct) {

		return productService.deleteProduct(idProduct);

	}

	/**
	 * Endpoint to get all products
	 * 
	 * @return ResponseEntity<List<ProductRSDTO>> -> products list and http status
	 *
	 */
	@GetMapping(path = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductRSDTO>> getAllProducts() {

		return productService.getAllProducts();

	}

}
