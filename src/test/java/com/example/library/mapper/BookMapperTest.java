package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void mapToBook() {
        BookDto bookDto = new BookDto(1L, "Test", "Milosz", LocalDate.now());

        Book book = bookMapper.mapToBook(bookDto);

        assertAll(
                () -> assertEquals(bookDto.getId(), book.getId()),
                () -> assertEquals(bookDto.getTitle(), book.getTitle()),
                () -> assertEquals(bookDto.getAuthor(), book.getAuthor()),
                () -> assertEquals(bookDto.getPublicationDate(), book.getPublicationDate())
        );
    }

    @Test
    void mapToBookDto() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());

        BookDto bookDto = bookMapper.mapToBookDto(book);

        assertAll(
                () -> assertEquals(book.getId(), bookDto.getId()),
                () -> assertEquals(book.getTitle(), bookDto.getTitle()),
                () -> assertEquals(book.getAuthor(), bookDto.getAuthor()),
                () -> assertEquals(book.getPublicationDate(), bookDto.getPublicationDate())
        );
    }
}