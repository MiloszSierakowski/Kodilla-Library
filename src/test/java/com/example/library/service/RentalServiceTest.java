package com.example.library.service;

import com.example.library.controller.RentalNotFoundException;
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
    private Rental savedRental;

    @BeforeEach
    void setUp() {
        savedBook = bookRepository.save(new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>()));
        savedCopyOfBook = copyOfBookRepository.save(new CopyOfBook(1L, savedBook, "Test copy", false, new ArrayList<>()));
        savedReader = readerRepository.save(new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>()));
    }

    @AfterEach
    void cleanUp() {
        copyOfBookRepository.deleteById(savedCopyOfBook.getId());
        bookRepository.deleteById(savedBook.getId());
        readerRepository.deleteById(savedReader.getId());
    }

    @Test
    void testSaveNewRental() {
        Rental rental = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());

        savedRental = rentalService.saveNewRental(rental);
        Optional<Rental> searchedRental = rentalRepository.findById(savedRental.getId());
        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());
        Optional<CopyOfBook> searchedCopyOfBook = copyOfBookRepository.findById(savedCopyOfBook.getId());

        try {
            assertTrue(searchedRental.isPresent());
            assertTrue(searchedReader.isPresent());
            assertTrue(searchedCopyOfBook.isPresent());

            assertEquals(savedRental.getId(), searchedReader.get().getRentalList().get(0).getId());
            assertEquals(savedRental.getId(), searchedCopyOfBook.get().getRentalList().get(0).getId());

            assertEquals(savedRental.getRentDate(), searchedRental.get().getRentDate());
            assertEquals(savedRental.getReturnDate(), searchedRental.get().getReturnDate());
        } finally {
            rentalRepository.deleteById(savedRental.getId());
        }
    }

    @Test
    void testFindById() throws RentalNotFoundException {
        Rental rental = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());
        savedRental = rentalRepository.save(rental);

        Rental searchedRental = rentalService.findById(savedRental.getId());
        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());
        Optional<CopyOfBook> searchedCopyOfBook = copyOfBookRepository.findById(savedCopyOfBook.getId());

        try {
            assertTrue(searchedReader.isPresent());
            assertTrue(searchedCopyOfBook.isPresent());

            assertEquals(savedRental.getId(), searchedReader.get().getRentalList().get(0).getId());
            assertEquals(savedRental.getId(), searchedCopyOfBook.get().getRentalList().get(0).getId());

            assertEquals(savedRental.getRentDate(), searchedRental.getRentDate());
            assertEquals(savedRental.getReturnDate(), searchedRental.getReturnDate());
        } finally {
            rentalRepository.deleteById(searchedRental.getId());
        }
    }
}