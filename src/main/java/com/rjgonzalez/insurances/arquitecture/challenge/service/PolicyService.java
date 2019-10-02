package com.rjgonzalez.insurances.arquitecture.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;

/**
 * Policy Service Interface
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
public interface PolicyService {

	public ResponseEntity<Void> addPolicy(PolicyRQDTO policyRQDTO);

	public ResponseEntity<Void> deletePolicy(Long idPolicy);

	public ResponseEntity<PolicyRSDTO> getPolicy(Long idPolicy);

	public ResponseEntity<List<PolicyRSDTO>> getAllPolicies();

	public ResponseEntity<PolicyRSDTO> updatePolicy(Long idPolicy, PolicyRQDTO policyRQDTO);
}
