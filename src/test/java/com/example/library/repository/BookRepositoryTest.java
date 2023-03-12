package com.example.library.repository;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void createTest() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(),
                new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "Lol", false, null);

        book.getCopyOfBookList().add(copyOfBook);

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getPublicationDate(), savedBook.getPublicationDate());
        assertEquals(book.getCopyOfBookList().size(), savedBook.getCopyOfBookList().size());
    }

    @Test
    void readTest() {
        Book book = new Book(2L, "Test2", "MiloszS", LocalDate.now(),
                new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(2L, book, "Lol2", false, null);

        book.getCopyOfBookList().add(copyOfBook);

        Book savedBook = bookRepository.save(book);
        Optional<Book> optionalBook = bookRepository.findById(savedBook.getId());
        Book unpackedBook = optionalBook.orElse(null);

        assertNotNull(unpackedBook);
        assertEquals(savedBook.getTitle(), unpackedBook.getTitle());
        assertEquals(savedBook.getAuthor(), unpackedBook.getAuthor());
        assertEquals(savedBook.getPublicationDate(), unpackedBook.getPublicationDate());
        assertEquals(savedBook.getCopyOfBookList().size(), unpackedBook.getCopyOfBookList().size());
    }

    @Test
    void updateTest() {
        Book book = new Book(3L, "Test", "Milosz", LocalDate.now(),
                new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(3L, book, "Lol", false, null);

        book.getCopyOfBookList().add(copyOfBook);

        Book savedBook = bookRepository.save(book);

        savedBook.setTitle("Update Test");

        Book updatedBook = bookRepository.save(savedBook);

        assertNotNull(updatedBook.getId());
        assertEquals(savedBook.getTitle(), updatedBook.getTitle());
        assertEquals(savedBook.getAuthor(), updatedBook.getAuthor());
        assertEquals(savedBook.getPublicationDate(), updatedBook.getPublicationDate());
        assertEquals(savedBook.getCopyOfBookList().size(), updatedBook.getCopyOfBookList().size());
    }

    @Test
    void deleteTest() {
        Book book = new Book(4L, "Test2", "MiloszS", LocalDate.now(),
                new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(4L, book, "Lol2", false, null);

        book.getCopyOfBookList().add(copyOfBook);

        Book savedBook = bookRepository.save(book);
        bookRepository.deleteById(savedBook.getId());
        Optional<Book> optionalBook = bookRepository.findById(savedBook.getId());
        //todo zobaczyć kaskadow
        assertFalse(optionalBook.isPresent());
    }
}