package com.example.library.repository;

import com.example.library.domain.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental,Long> {
}
