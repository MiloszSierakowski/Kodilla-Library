package com.example.library.service;

import com.example.library.controller.BookNotFoundException;
import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BootstrapMethodError::new);
    }
}
