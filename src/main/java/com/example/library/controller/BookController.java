package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
        return ResponseEntity.ok().build();
    }
}
