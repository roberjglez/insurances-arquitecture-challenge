package com.rjgonzalez.insurances.arquitecture.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRQDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.dto.PolicyRSDTO;
import com.rjgonzalez.insurances.arquitecture.challenge.entity.PolicyEntity;
import com.rjgonzalez.insurances.arquitecture.challenge.repository.PolicyRepository;
import com.rjgonzalez.insurances.arquitecture.challenge.service.PolicyService;

/**
 * Policy Service Implementation
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PolicyRepository policyRepository;

	@Override
	public ResponseEntity<PolicyRSDTO> addPolicy(PolicyRQDTO policyRQDTO) {

		PolicyEntity policyRQEntity = modelMapper.map(policyRQDTO, PolicyEntity.class);

		PolicyEntity policyRSEntity = policyRepository.save(policyRQEntity);

		PolicyRSDTO policyResponse = modelMapper.map(policyRSEntity, PolicyRSDTO.class);

		return new ResponseEntity<>(policyResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PolicyRSDTO> deletePolicy(Long idPolicy) {

		policyRepository.deleteById(idPolicy);

		PolicyRSDTO policyRSEntity = new PolicyRSDTO();

		return new ResponseEntity<>(policyRSEntity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PolicyRSDTO> getPolicy(Long idPolicy) {

		Optional<PolicyEntity> policyEntity = policyRepository.findById(idPolicy);

		if (policyEntity.isPresent()) {

			PolicyRSDTO policyResponse = modelMapper.map(policyEntity.get(), PolicyRSDTO.class);

			return new ResponseEntity<>(policyResponse, HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<List<PolicyRSDTO>> getAllPolicies() {

		List<PolicyEntity> policiesListEntity = policyRepository.findAll();

		List<PolicyRSDTO> policiesListDTO = modelMapper.map(policiesListEntity, new TypeToken<List<PolicyRSDTO>>() {
		}.getType());

		return new ResponseEntity<>(policiesListDTO, HttpStatus.OK);
	}

}
