package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private CopyOfBookService copyOfBookService;

    @Test
    void saveBook() {
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

        Optional<CopyOfBook> searchedCopyOfBook = copyOfBookService.findById(savedBook.getCopyOfBookList().get(0).getId());
        Optional<CopyOfBook> searchedCopyOfBook1 = copyOfBookService.findById(savedBook.getCopyOfBookList().get(1).getId());
        Optional<CopyOfBook> searchedCopyOfBook2 = copyOfBookService.findById(savedBook.getCopyOfBookList().get(2).getId());

        assertTrue(searchedCopyOfBook.isPresent());
        assertEquals(copyOfBook.getStatus(), searchedCopyOfBook.get().getStatus());
        assertTrue(searchedCopyOfBook1.isPresent());
        assertEquals(copyOfBook1.getStatus(), searchedCopyOfBook1.get().getStatus());
        assertTrue(searchedCopyOfBook2.isPresent());
        assertEquals(copyOfBook2.getStatus(), searchedCopyOfBook2.get().getStatus());

        bookRepository.deleteById(savedBook.getId());
    }

    @Test
    void findBookById() {
        Book saveBook = bookService.saveBook(new Book(1L, "Milosz testuje", "Sie",
                LocalDate.now(), new ArrayList<>()));

        Optional<Book> searchedBook = bookService.findById(saveBook.getId());

        try {
            assertTrue(searchedBook.isPresent());
            assertEquals(saveBook.getTitle(), searchedBook.get().getTitle());
            assertEquals(saveBook.getAuthor(), searchedBook.get().getAuthor());
            assertEquals(saveBook.getPublicationDate(), searchedBook.get().getPublicationDate());
            assertEquals(0, searchedBook.get().getCopyOfBookList().size());
        } finally {
            bookRepository.deleteById(saveBook.getId());
        }

    }

}