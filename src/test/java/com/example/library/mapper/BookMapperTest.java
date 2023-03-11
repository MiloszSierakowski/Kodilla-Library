package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import com.example.library.domain.CopyOfBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void mapToBook() {
        List<CopyOfBook> copyOfBookList = new ArrayList<>();
        BookDto bookDto = new BookDto(1L, "Test", "Milosz", LocalDate.now(), copyOfBookList);
        Book book = bookMapper.mapToBook(bookDto);

        assertAll(
                ()-> assertEquals(bookDto.getId(),book.getId()),
                ()-> assertEquals(bookDto.getTitle(),book.getTitle()),
                ()-> assertEquals(bookDto.getAuthor(),book.getAuthor()),
                ()-> assertEquals(bookDto.getPublicationDate(),book.getPublicationDate()),
                ()-> assertTrue(book.getCopyOfBookList().isEmpty())
        );
    }

    @Test
    void mapToBookDto() {
        List<CopyOfBook> copyOfBookList = new ArrayList<>();
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), copyOfBookList);
        BookDto bookDto = bookMapper.mapToBookDto(book);

        assertAll(
                ()-> assertEquals(book.getId(),bookDto.getId()),
                ()-> assertEquals(book.getTitle(),bookDto.getTitle()),
                ()-> assertEquals(book.getAuthor(),bookDto.getAuthor()),
                ()-> assertEquals(book.getPublicationDate(),bookDto.getPublicationDate()),
                ()-> assertTrue(bookDto.getCopyOfBookList().isEmpty())
        );
    }
}