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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalServiceTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private CopyOfBookRepository copyOfBookRepository;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalRepository rentalRepository;
    private Book savedBook;
    private Reader savedReader;
    private CopyOfBook savedCopyOfBook;

    @BeforeEach
    void setUp() {
        savedBook = bookRepository.save(new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>()));
        savedCopyOfBook = copyOfBookRepository.save(new CopyOfBook(1L, savedBook, "Test copy", false, new ArrayList<>()));
        savedReader = readerRepository.save(new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>()));
    }

    @AfterEach
    void cleanUp() {
        rentalRepository.deleteAll();
//        copyOfBookRepository.deleteAll();
//        bookRepository.deleteAll();
//        readerRepository.deleteAll();
    }

    @Test
    void testSaveNewRental() {
        Rental rental = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());

        Rental savedRental = rentalService.saveNewRental(rental);
        Optional<Rental> searchedRental = rentalRepository.findById(savedRental.getId());

        assertTrue(searchedRental.isPresent());
        assertEquals(savedReader.getFirstname(), searchedRental.get().getReader().getFirstname());
        assertEquals(savedCopyOfBook.getStatus(), searchedRental.get().getCopyOfBook().getStatus());
        assertEquals(savedRental.getRentDate(), searchedRental.get().getRentDate());
        assertEquals(savedRental.getReturnDate(), searchedRental.get().getReturnDate());


    }

    @Test
    void testFindById() {
        Rental rental = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());
        Rental savedRental = rentalRepository.save(rental);

        Optional<Rental> searchedRental = rentalService.findById(savedRental.getId());

        assertTrue(searchedRental.isPresent());
        assertEquals(savedReader.getFirstname(), searchedRental.get().getReader().getFirstname());
        assertEquals(savedCopyOfBook.getStatus(), searchedRental.get().getCopyOfBook().getStatus());
        assertEquals(savedRental.getRentDate(), searchedRental.get().getRentDate());
        assertEquals(savedRental.getReturnDate(), searchedRental.get().getReturnDate());

    }
}