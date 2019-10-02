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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.PolicyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to manage policies
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@Api(value = "API Rest for insurance policies.")
@RequestMapping(value = "/api-challenge/policies")
public class PolicyController {

	@Autowired
	PolicyService policyService;

	/**
	 * Endpoint to add new policies
	 * 
	 * @param policyRQDTO -> policy with all the information
	 * @return ResponseEntity<PolicyRSDTO> -> policy and http status
	 *
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add new policy of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Policy created succesfully") })
	public ResponseEntity<Void> addPolicy(
			@ApiParam(value = "New policy object to store in database table", required = true) @RequestBody PolicyRQDTO policyRQDTO) {

		return policyService.addPolicy(policyRQDTO);

	}

	/**
	 * Endpoint to get a policy by ID
	 * 
	 * @param idPolicy -> id policy that we want to get
	 * @return ResponseEntity<PolicyRSDTO> -> policy and http status
	 *
	 */
	@GetMapping(path = "/{idPolicy}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve a policy of the insurance company.", response = PolicyRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Policy found succesfully"),
			@ApiResponse(code = 204, message = "Policy not found") })
	public ResponseEntity<PolicyRSDTO> getPolicy(
			@ApiParam(value = "Policy id that we want to get from database table", required = true) @PathVariable Long idPolicy) {

		return policyService.getPolicy(idPolicy);

	}

	/**
	 * Endpoint to delete a policy by ID
	 * 
	 * @param idPolicy -> id policy that we want to delete
	 * @return ResponseEntity<PolicyRSDTO> -> policy and http status
	 *
	 */
	@DeleteMapping(path = "/{idPolicy}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Delete a policy of the insurance company.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Policy deleted succesfully") })
	public ResponseEntity<Void> deletePolicy(
			@ApiParam(value = "Policy id that we want to delete in database table", required = true) @PathVariable Long idPolicy) {

		return policyService.deletePolicy(idPolicy);

	}

	/**
	 * Endpoint to get all policies
	 * 
	 * @return ResponseEntity<List<PolicyRSDTO>> -> policies list and http status
	 *
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve the list of all policies of the insurance company.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Policies list found succesfully") })
	public ResponseEntity<List<PolicyRSDTO>> getAllPolicies() {

		return policyService.getAllPolicies();

	}

	/**
	 * Endpoint to update a specific policy
	 * 
	 * @param idPolicy    -> id policy that we want to update
	 * @param policyRQDTO -> policy with all the information
	 * @return ResponseEntity<PolicyRSDTO> -> policy and http status
	 *
	 */
	@PutMapping(path = "/{idPolicy}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a policy of the insurance company.", response = PolicyRSDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Policy updated succesfully") })
	public ResponseEntity<PolicyRSDTO> updatePolicy(
			@ApiParam(value = "Policy id that we want to update in database table", required = true) @PathVariable Long idPolicy,
			@ApiParam(value = "Policy object to update in database table", required = true) @RequestBody PolicyRQDTO policyRQDTO) {

		return policyService.updatePolicy(idPolicy, policyRQDTO);

	}

}
