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

/**
 * Controller to manage policies
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@Api(value = "API Rest for insurance policies.")
@RequestMapping({ "api-challenge/policies" })
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
	public ResponseEntity<PolicyRSDTO> addPolicy(@RequestBody PolicyRQDTO policyRQDTO) {

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
	@ApiOperation(value = "Retrieve a policy of the insurance company.")
	public ResponseEntity<PolicyRSDTO> getPolicy(@PathVariable Long idPolicy) {

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
	public ResponseEntity<PolicyRSDTO> deletePolicy(@PathVariable Long idPolicy) {

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
	@ApiOperation(value = "Retrieve the list of all policies of the insurance company.")
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
	@ApiOperation(value = "Update a policy of the insurance company.")
	public ResponseEntity<PolicyRSDTO> updatePolicy(@PathVariable Long idPolicy, @RequestBody PolicyRQDTO policyRQDTO) {

		return policyService.updatePolicy(idPolicy, policyRQDTO);

	}

}
