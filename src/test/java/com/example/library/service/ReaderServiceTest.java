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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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
    private Reader savedReader;

    @AfterEach
    void cleanUp() {
        rentalRepository.deleteById(savedReader.getId());
    }

    @Test
    void saveReaderWithoutRental() {
        Reader reader = new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>());

        savedReader = readerService.saveReader(reader);
        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());

        assertTrue(searchedReader.isPresent());
        assertEquals(savedReader.getFirstname(), searchedReader.get().getFirstname());
        assertEquals(savedReader.getLastname(), searchedReader.get().getLastname());
        assertEquals(savedReader.getRegistrationDate(), searchedReader.get().getRegistrationDate());
        assertEquals(0, searchedReader.get().getRentalList().size());
    }

    @Test
    void saveReaderWithRental() {

        savedReader = readerService.saveReader(new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>()));
        Book savedBook = bookRepository.save(new Book(1L, "Testowa ksiazka", "Milosz", LocalDate.now(), new ArrayList<>()));
        CopyOfBook savedCopyOfBook = copyOfBookRepository.save(new CopyOfBook(1L, savedBook, "New one", true, new ArrayList<>()));

//        book.getCopyOfBookList().add(copyOfBook);

//        reader.getRentalList().add(rental);
//        reader.getRentalList().add(rental1);

//        copyOfBook.getRentalList().add(rental);
//        copyOfBook.getRentalList().add(rental1);

        Rental rental = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());
        Rental rental1 = new Rental(1L, savedCopyOfBook, savedReader, LocalDate.now(), LocalDate.now());

        rentalRepository.save(rental);
        rentalRepository.save(rental1);

        Optional<Reader> searchedReader = readerRepository.findById(savedReader.getId());

        assertTrue(searchedReader.isPresent());
        assertEquals(savedReader.getFirstname(), searchedReader.get().getFirstname());
        assertEquals(savedReader.getLastname(), searchedReader.get().getLastname());
        assertEquals(savedReader.getRegistrationDate(), searchedReader.get().getRegistrationDate());
        assertEquals(2, searchedReader.get().getRentalList().size());

        assertEquals(rental.getRentDate(), searchedReader.get().getRentalList().get(0).getRentDate());
        assertEquals(rental1.getRentDate(), searchedReader.get().getRentalList().get(1).getRentDate());

        bookRepository.deleteById(savedBook.getId());
    }
}