package com.example.library.mapper;

import com.example.library.domain.Reader;
import com.example.library.domain.ReaderDto;
import com.example.library.domain.Rental;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReaderMapperTest {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    void mapToReader() {
        ReaderDto readerDto = new ReaderDto(1L, "Milosz", "Sierakowski", LocalDate.now());

        Reader reader = readerMapper.mapToReader(readerDto);

        assertAll(
                () -> assertEquals(readerDto.getId(), reader.getId()),
                () -> assertEquals(readerDto.getFirstname(), reader.getFirstname()),
                () -> assertEquals(readerDto.getLastname(), reader.getLastname()),
                () -> assertEquals(readerDto.getRegistrationDate(), reader.getRegistrationDate())
        );
    }

    @Test
    void mapToReaderDto() {
        List<Rental> rentalList = new ArrayList<>();
        Reader reader = new Reader(1L, "Milosz", "Sierakowski", LocalDate.now(), rentalList);
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);

        assertAll(
                () -> assertEquals(reader.getId(), readerDto.getId()),
                () -> assertEquals(reader.getFirstname(), readerDto.getFirstname()),
                () -> assertEquals(reader.getLastname(), readerDto.getLastname()),
                () -> assertEquals(reader.getRegistrationDate(), readerDto.getRegistrationDate())
        );

    }
}