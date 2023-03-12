package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.domain.Reader;
import com.example.library.domain.Rental;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CopyOfBookRepository;
import com.example.library.repository.ReaderRepository;
import com.example.library.repository.RentalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CopyOfBookServiceTest {

    @Autowired
    private CopyOfBookService copyOfBookService;
    @Autowired
    private CopyOfBookRepository copyOfBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ReaderRepository readerRepository;

    private Book savedBook;
    private Reader savedReader;

    @BeforeEach
    void setUp() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());
        book.getCopyOfBookList().add(new CopyOfBook(1L, book, "in good shape", false, new ArrayList<>()));
        book.getCopyOfBookList().add(new CopyOfBook(2L, book, "coffee stains", true, new ArrayList<>()));
        book.getCopyOfBookList().add(new CopyOfBook(3L, book, "brand new", false, new ArrayList<>()));

        savedReader = readerRepository.save(new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>()));

        book.getCopyOfBookList().get(1).getRentalList().add(
                new Rental(1L, book.getCopyOfBookList().get(1), savedReader, LocalDate.now(), LocalDate.now())
        );
        book.getCopyOfBookList().get(1).getRentalList().add(
                new Rental(2L, book.getCopyOfBookList().get(1), savedReader, LocalDate.now(), LocalDate.now())
        );

        savedBook = bookRepository.save(book);
    }

    @AfterEach
    void clenUp() {
        rentalRepository.deleteById(savedBook.getCopyOfBookList().get(1).getRentalList().get(0).getId());
        rentalRepository.deleteById(savedBook.getCopyOfBookList().get(1).getRentalList().get(1).getId());
        copyOfBookRepository.deleteById(savedBook.getCopyOfBookList().get(0).getId());
        copyOfBookRepository.deleteById(savedBook.getCopyOfBookList().get(1).getId());
        copyOfBookRepository.deleteById(savedBook.getCopyOfBookList().get(2).getId());
//        readerRepository.deleteById(savedReader.getId());
//        bookRepository.deleteById(savedBook.getId());
    }

    @Test
    void saveCopyOfBook() {
        CopyOfBook copyOfBook = new CopyOfBook(1L, savedBook, "new book", false, new ArrayList<>());

//        copyOfBook.getRentalList().add(rental);
//        copyOfBook.getRentalList().add(rental1);

        CopyOfBook saveCopyOfBook = copyOfBookService.saveCopyOfBook(copyOfBook);

        Rental rental = new Rental(3L, saveCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());
        Rental rental1 = new Rental(4L, saveCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());
        Rental savedRental = rentalRepository.save(rental);
        Rental savedRental1 = rentalRepository.save(rental1);

        Optional<CopyOfBook> afterUpdate = copyOfBookService.findById(saveCopyOfBook.getId());

        try {
            assertNotNull(saveCopyOfBook.getId());
            assertEquals(savedBook.getId(), saveCopyOfBook.getBook().getId());
            assertEquals(copyOfBook.getStatus(), saveCopyOfBook.getStatus());
            assertEquals(copyOfBook.isRental(), saveCopyOfBook.isRental());
            assertEquals(copyOfBook.getRentalList().size(), saveCopyOfBook.getRentalList().size());

            assertTrue(afterUpdate.isPresent());
            Optional<Rental> searchedRental = rentalRepository.findById(afterUpdate.get().getRentalList().get(0).getId());
            Optional<Rental> searchedRental1 = rentalRepository.findById(afterUpdate.get().getRentalList().get(1).getId());

            assertTrue(searchedRental.isPresent());
            assertTrue(searchedRental1.isPresent());
            assertEquals(rental.getRentDate(), searchedRental.get().getRentDate());
            assertEquals(rental1.getReturnDate(), searchedRental1.get().getReturnDate());
        } finally {
            rentalRepository.deleteById(savedRental.getId());
            rentalRepository.deleteById(savedRental1.getId());
            copyOfBookRepository.deleteById(afterUpdate.get().getId());
        }

    }

    @Test
    void findById() {
        CopyOfBook copyOfBook = new CopyOfBook(1L, savedBook, "new book", false, new ArrayList<>());

        CopyOfBook saveCopyOfBook = copyOfBookService.saveCopyOfBook(copyOfBook);
        Optional<CopyOfBook> searchedCopyOfBook = copyOfBookService.findById(saveCopyOfBook.getId());

        assertTrue(searchedCopyOfBook.isPresent());
        assertEquals(copyOfBook.getStatus(), searchedCopyOfBook.get().getStatus());
        assertEquals(copyOfBook.isRental(), searchedCopyOfBook.get().isRental());
        assertEquals(0, searchedCopyOfBook.get().getRentalList().size());
    }

    @Test
    void findAvailableCopyOfBook() {
        CopyOfBook copyOfBook = new CopyOfBook(1L, savedBook, "new book", true, new ArrayList<>());

        copyOfBookService.saveCopyOfBook(copyOfBook);

        List<CopyOfBook> availableToRent = copyOfBookService.findAvailableCopyOfBook(savedBook.getTitle());

        availableToRent.forEach(copyOfBook1 -> System.out.println(copyOfBook1.getId()));

        assertEquals(2, availableToRent.size());

    }
}