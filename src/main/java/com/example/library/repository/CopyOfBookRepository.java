package com.example.library.repository;

import com.example.library.domain.CopyOfBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
}
