package com.rjgonzalez.insurances.arquitecture.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.ProductRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to manage products
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@Api(value = "API Rest for insurance products.")
@RequestMapping(value = "/api-challenge/products")
public class ProductController {

	ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Endpoint to add new products
	 * 
	 * @param ProductRQDTO -> product with all the information
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add new product of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Product created succesfully") })
	public ResponseEntity<Void> addProduct(
			@ApiParam(value = "New product object to store in database table", required = true) @RequestBody ProductRQDTO productRQDTO) {

		return productService.addProduct(productRQDTO);

	}

	/**
	 * Endpoint to get a product by ID
	 * 
	 * @param idProduct -> id product that we want to get
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@GetMapping(path = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieve a product of the insurance company.", response = ProductRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product found succesfully"),
			@ApiResponse(code = 204, message = "Product not found") })
	public ResponseEntity<ProductRSDTO> getProduct(
			@ApiParam(value = "Product id that we want to get from database table", required = true) @PathVariable Long idProduct) {

		return productService.getProduct(idProduct);

	}

	/**
	 * Endpoint to delete a product by ID
	 * 
	 * @param idProduct -> id product that we want to delete
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@DeleteMapping(path = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a product of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product deleted succesfully") })
	public ResponseEntity<Void> deleteProduct(
			@ApiParam(value = "Product id that we want to delete in database table", required = true) @PathVariable Long idProduct) {

		return productService.deleteProduct(idProduct);

	}

	/**
	 * Endpoint to get all products
	 * 
	 * @return ResponseEntity<List<ProductRSDTO>> -> products list and http status
	 *
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieve the list of all products of the insurance company.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Products list found succesfully") })
	public ResponseEntity<List<ProductRSDTO>> getAllProducts() {

		return productService.getAllProducts();

	}

	/**
	 * Endpoint to update a specific product
	 * 
	 * @param idProduct    -> id product that we want to update
	 * @param productRQDTO -> product with all the information
	 * @return ResponseEntity<ProductRSDTO> -> product and http status
	 *
	 */
	@PutMapping(path = "/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a product of the insurance company.", response = ProductRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product updated succesfully") })
	public ResponseEntity<ProductRSDTO> updateProduct(
			@ApiParam(value = "Product id that we want to update in database table", required = true) @PathVariable Long idProduct,
			@ApiParam(value = "Product object to update in database table", required = true) @RequestBody ProductRQDTO productRQDTO) {

		return productService.updateProduct(idProduct, productRQDTO);

	}

}
