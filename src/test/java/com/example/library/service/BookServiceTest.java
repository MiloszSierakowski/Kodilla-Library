package com.example.library.service;

import com.example.library.controller.BookNotFoundException;
import com.example.library.controller.CopyOfBookNotFoundException;
import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyOfBookService copyOfBookService;

    @Test
    void saveBook() throws CopyOfBookNotFoundException {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(),
                new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "pierwsza kopia", false, new ArrayList<>());
        CopyOfBook copyOfBook1 = new CopyOfBook(2L, book, "druga kopia", false, new ArrayList<>());
        CopyOfBook copyOfBook2 = new CopyOfBook(3L, book, "trzecia kopia", false, new ArrayList<>());

        book.getCopyOfBookList().add(copyOfBook);
        book.getCopyOfBookList().add(copyOfBook1);
        book.getCopyOfBookList().add(copyOfBook2);

        Book savedBook = bookService.saveBook(book);

        assertNotNull(savedBook.getId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getPublicationDate(), savedBook.getPublicationDate());
        assertEquals(book.getCopyOfBookList().size(), savedBook.getCopyOfBookList().size());

        CopyOfBook searchedCopyOfBook = copyOfBookService.findById(savedBook.getCopyOfBookList().get(0).getId());
        CopyOfBook searchedCopyOfBook1 = copyOfBookService.findById(savedBook.getCopyOfBookList().get(1).getId());
        CopyOfBook searchedCopyOfBook2 = copyOfBookService.findById(savedBook.getCopyOfBookList().get(2).getId());

        assertEquals(copyOfBook.getStatus(), searchedCopyOfBook.getStatus());
        assertEquals(copyOfBook1.getStatus(), searchedCopyOfBook1.getStatus());
        assertEquals(copyOfBook2.getStatus(), searchedCopyOfBook2.getStatus());

        bookRepository.deleteById(savedBook.getId());
    }

    @Test
    void findBookById() throws BookNotFoundException {
        Book saveBook = bookService.saveBook(new Book(1L, "Milosz testuje", "Sie",
                LocalDate.now(), new ArrayList<>()));

        Book searchedBook = bookService.findById(saveBook.getId());

        try {
            assertEquals(saveBook.getTitle(), searchedBook.getTitle());
            assertEquals(saveBook.getAuthor(), searchedBook.getAuthor());
            assertEquals(saveBook.getPublicationDate(), searchedBook.getPublicationDate());
            assertEquals(0, searchedBook.getCopyOfBookList().size());
        } finally {
            bookRepository.deleteById(saveBook.getId());
        }

    }

}