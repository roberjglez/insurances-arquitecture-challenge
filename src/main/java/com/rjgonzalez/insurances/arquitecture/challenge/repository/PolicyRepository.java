package com.rjgonzalez.insurances.arquitecture.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rjgonzalez.insurances.arquitecture.challenge.entity.PolicyEntity;

/**
 * Policy Repository
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {

}
