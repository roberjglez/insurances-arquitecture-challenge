package com.rjgonzalez.insurances.arquitecture.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rjgonzalez.insurances.arquitecture.challenge.entity.ProductEntity;

/**
 * Product Repository
 * 
 * @author Roberto Jesus Gonzalez Carrato Pozo
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
