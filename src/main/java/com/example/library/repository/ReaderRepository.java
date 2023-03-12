package com.example.library.repository;

import com.example.library.domain.Reader;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
