package com.example.library.mapper;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.Reader;
import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalMapperTest {

    @Autowired
    private RentalMapper rentalMapper;

    @Test
    void mapToRental() {
        CopyOfBook copyOfBook = new CopyOfBook();
        Reader reader = new Reader();
        RentalDto rentalDto = new RentalDto(1L, copyOfBook, reader, LocalDate.now(), LocalDate.now());

        Rental rental = rentalMapper.mapToRental(rentalDto);

        assertAll(
                () -> assertEquals(rentalDto.getId(), rental.getId()),
                () -> assertEquals(rentalDto.getCopyOfBook(), rental.getCopyOfBook()),
                () -> assertEquals(rentalDto.getReader(), rental.getReader()),
                () -> assertEquals(rentalDto.getRentDate(), rental.getRentDate()),
                () -> assertEquals(rentalDto.getReturnDate(), rental.getReturnDate())
        );
    }

    @Test
    void mapToRentalDto() {
        CopyOfBook copyOfBook = new CopyOfBook();
        Reader reader = new Reader();
        Rental rental = new Rental(1L, copyOfBook, reader, LocalDate.now(), LocalDate.now());

        RentalDto rentalDto = rentalMapper.mapToRentalDto(rental);

        assertAll(
                () -> assertEquals(rental.getId(), rentalDto.getId()),
                () -> assertEquals(rental.getCopyOfBook(), rentalDto.getCopyOfBook()),
                () -> assertEquals(rental.getReader(), rentalDto.getReader()),
                () -> assertEquals(rental.getRentDate(), rentalDto.getRentDate()),
                () -> assertEquals(rental.getReturnDate(), rentalDto.getReturnDate())
        );
    }
}