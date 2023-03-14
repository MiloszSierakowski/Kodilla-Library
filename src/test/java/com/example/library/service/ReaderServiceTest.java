package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.domain.Reader;
import com.example.library.domain.Rental;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CopyOfBookRepository;
import com.example.library.repository.ReaderRepository;
import com.example.library.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReaderServiceTest {

    @Autowired
    private ReaderService readerService;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CopyOfBookRepository copyOfBookRepository;

    @Test
    void saveReaderWithoutRental() {
        Reader reader = new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>());

        Reader savedReader = readerService.saveReader(reader);
        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());

        try {
            assertTrue(searchedReader.isPresent());
            assertEquals(savedReader.getFirstname(), searchedReader.get().getFirstname());
            assertEquals(savedReader.getLastname(), searchedReader.get().getLastname());
            assertEquals(savedReader.getRegistrationDate(), searchedReader.get().getRegistrationDate());
            assertEquals(0, searchedReader.get().getRentalList().size());
        } finally {
            readerRepository.deleteById(savedReader.getId());
        }
    }

    @Test
    void saveReaderWithRental() {
        Reader savedReader = readerService.saveReader(new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>()));
        Book savedBook = bookRepository.save(new Book(1L, "Testowa ksiazka", "Milosz", LocalDate.now(), new ArrayList<>()));
        CopyOfBook savedCopyOfBook = copyOfBookRepository.save(new CopyOfBook(1L, savedBook, "New one", true, new ArrayList<>()));
        Rental savedRental = rentalRepository.save(new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now()));

        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());

        try {
            assertTrue(searchedReader.isPresent());
            assertEquals(savedReader.getFirstname(), searchedReader.get().getFirstname());
            assertEquals(savedReader.getLastname(), searchedReader.get().getLastname());
            assertEquals(savedReader.getRegistrationDate(), searchedReader.get().getRegistrationDate());
            assertEquals(1, searchedReader.get().getRentalList().size());
            assertEquals(savedRental.getRentDate(), searchedReader.get().getRentalList().get(0).getRentDate());
        } finally {
            readerRepository.deleteById(savedReader.getId());
            bookRepository.deleteById(savedBook.getId());
        }
    }
}