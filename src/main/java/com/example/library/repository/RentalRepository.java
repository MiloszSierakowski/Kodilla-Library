package com.example.library.repository;

import com.example.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental,Long> {
}
