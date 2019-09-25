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

import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.service.PolicyService;

/**
 * Controller to manage policies
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@RestController
@RequestMapping({ "api-challenge/policy" })
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
	@PostMapping(path = "/addPolicy", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
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
	@GetMapping(path = "/getPolicy/{idPolicy}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
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
	@DeleteMapping(path = "/deletePolicy/{idPolicy}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PolicyRSDTO> deletePolicy(@PathVariable Long idPolicy) {

		return policyService.deletePolicy(idPolicy);

	}

	/**
	 * Endpoint to get all policies
	 * 
	 * @return ResponseEntity<List<PolicyRSDTO>> -> policies list and http status
	 *
	 */
	@GetMapping(path = "/getAllPolicies", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<PolicyRSDTO>> getAllPolicies() {

		return policyService.getAllPolicies();

	}

}