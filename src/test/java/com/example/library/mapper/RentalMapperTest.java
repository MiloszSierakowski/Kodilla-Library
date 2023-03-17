package com.example.library.mapper;

import com.example.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalMapperTest {

    @Autowired
    private RentalMapper rentalMapper;

    @Test
    void mapToRental() {
        BookDto bookDto = new BookDto(1L, "Test", "Milosz", LocalDate.now());
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, bookDto.getId(), "Test copy", false);
        ReaderDto readerDto = new ReaderDto(1L, "Milosz", "S", LocalDate.now());
        RentalDto rentalDto = new RentalDto(1L, copyOfBookDto.getBookId(), readerDto.getId(), LocalDate.now(), LocalDate.now());

        Rental rental = rentalMapper.mapToRental(rentalDto);

        assertAll(
                () -> assertEquals(rentalDto.getId(), rental.getId()),
                () -> assertEquals(rentalDto.getRentDate(), rental.getRentDate()),
                () -> assertEquals(rentalDto.getReturnDate(), rental.getReturnDate())
        );
    }

    @Test
    void mapToRentalDto() {
        CopyOfBook copyOfBook = new CopyOfBook(1L, new Book(), "Test copy", false, new ArrayList<>());
        Reader reader = new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>());
        Rental rental = new Rental(1L, copyOfBook, reader, LocalDate.now(), LocalDate.now());

        RentalDto rentalDto = rentalMapper.mapToRentalDto(rental);

        assertAll(
                () -> assertEquals(rental.getId(), rentalDto.getId()),
                () -> assertEquals(rental.getCopyOfBook().getId(), rentalDto.getCopyOfBookId()),
                () -> assertEquals(rental.getReader().getId(), rentalDto.getReaderId()),
                () -> assertEquals(rental.getRentDate(), rentalDto.getRentDate()),
                () -> assertEquals(rental.getReturnDate(), rentalDto.getReturnDate())
        );
    }

    @Test
    void testMapToListRental() {
        BookDto bookDto = new BookDto(1L, "Test", "Milosz", LocalDate.now());
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, bookDto.getId(), "Testowy", false);
        ReaderDto readerDto = new ReaderDto(1L, "Milosz", "Sierakowski", LocalDate.now());

        List<RentalDto> rentalDtoList = List.of(
                new RentalDto(1L, copyOfBookDto.getBookId(), readerDto.getId(), LocalDate.now(), LocalDate.now()),
                new RentalDto(2L, copyOfBookDto.getBookId(), readerDto.getId(), LocalDate.now(), LocalDate.now()),
                new RentalDto(3L, copyOfBookDto.getBookId(), readerDto.getId(), LocalDate.now(), LocalDate.now())
        );


        List<Rental> rentalList = rentalMapper.mapToListRental(rentalDtoList);

        assertEquals(rentalDtoList.size(), rentalList.size());

        IntStream.range(0, Math.min(rentalDtoList.size(), rentalList.size()))
                .forEach(i -> {
                    assertEquals(rentalDtoList.get(i).getId(), rentalList.get(i).getId());
                    assertEquals(rentalDtoList.get(i).getRentDate(), rentalList.get(i).getRentDate());
                    assertEquals(rentalDtoList.get(i).getReturnDate(), rentalList.get(i).getReturnDate());
                });
    }

    @Test
    void testMapToListRentalDto() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "Testowy", false, new ArrayList<>());
        Reader reader = new Reader(1L, "Milosz", "Sierakowski", LocalDate.now(), new ArrayList<>());

        List<Rental> rentalList = List.of(
                new Rental(1L, copyOfBook, reader, LocalDate.now(), LocalDate.now()),
                new Rental(2L, copyOfBook, reader, LocalDate.now(), LocalDate.now()),
                new Rental(3L, copyOfBook, reader, LocalDate.now(), LocalDate.now())
        );


        List<RentalDto> rentalDtoList = rentalMapper.mapToListRentalDto(rentalList);

        assertEquals(rentalList.size(), rentalDtoList.size());

        IntStream.range(0, Math.min(rentalList.size(), rentalDtoList.size()))
                .forEach(i -> {
                    assertEquals(rentalList.get(i).getId(), rentalDtoList.get(i).getId());
                    assertEquals(rentalList.get(i).getCopyOfBook().getId(), rentalDtoList.get(i).getCopyOfBookId());
                    assertEquals(rentalList.get(i).getReader().getId(), rentalDtoList.get(i).getReaderId());
                    assertEquals(rentalList.get(i).getRentDate(), rentalDtoList.get(i).getRentDate());
                    assertEquals(rentalList.get(i).getReturnDate(), rentalDtoList.get(i).getReturnDate());
                });
    }

}