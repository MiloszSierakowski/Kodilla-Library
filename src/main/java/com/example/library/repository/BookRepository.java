package com.example.library.repository;

import com.example.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
