package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import com.example.library.domain.Rental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CopyOfBookMapperTest {

    @Autowired
    private CopyOfBookMapper copyOfBookMapper;

    @Test
    void mapToCopyOfBook() {
        List<CopyOfBook> copyOfBookList = new ArrayList<>();
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), copyOfBookList);
        List<Rental> rentalList = new ArrayList<>();
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, book, "Testowy", false, rentalList);

        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);

        assertAll(
                ()-> assertEquals(copyOfBookDto.getId(),copyOfBook.getId()),
                ()-> assertEquals(copyOfBookDto.getBook(),copyOfBook.getBook()),
                ()-> assertEquals(copyOfBookDto.getStatus(),copyOfBook.getStatus()),
                ()-> assertEquals(copyOfBookDto.isRental(),copyOfBook.isRental()),
                ()-> assertTrue(copyOfBook.getRentalList().isEmpty())
        );

    }

    @Test
    void mapToCopyOfBookDto() {
        List<CopyOfBook> copyOfBookList = new ArrayList<>();
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), copyOfBookList);
        List<Rental> rentalList = new ArrayList<>();
        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "Testowy", false, rentalList);

        CopyOfBookDto copyOfBookDto = copyOfBookMapper.mapToCopyOfBookDto(copyOfBook);

        assertAll(
                ()-> assertEquals(copyOfBook.getId(),copyOfBookDto.getId()),
                ()-> assertEquals(copyOfBook.getBook(),copyOfBookDto.getBook()),
                ()-> assertEquals(copyOfBook.getStatus(),copyOfBookDto.getStatus()),
                ()-> assertEquals(copyOfBook.isRental(),copyOfBookDto.isRental()),
                ()-> assertTrue(copyOfBookDto.getRentalList().isEmpty())
        );
    }
}