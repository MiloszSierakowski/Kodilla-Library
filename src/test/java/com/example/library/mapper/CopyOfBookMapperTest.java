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
class CopyOfBookMapperTest {

    @Autowired
    private CopyOfBookMapper copyOfBookMapper;


    @Test
    void testMapToCopyOfBook() {
        BookDto bookDto = new BookDto(1L, "Test", "Milosz", LocalDate.now());
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, bookDto.getId(), "Testowy", false);

        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);

        assertAll(
                () -> assertEquals(copyOfBookDto.getId(), copyOfBook.getId()),
                () -> assertEquals(copyOfBookDto.getStatus(), copyOfBook.getStatus()),
                () -> assertEquals(copyOfBookDto.isRented(), copyOfBook.isRented())
        );
    }

    @Test
    void testMapToCopyOfBookDto() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());
        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "Testowy", false, new ArrayList<>());

        CopyOfBookDto copyOfBookDto = copyOfBookMapper.mapToCopyOfBookDto(copyOfBook);

        assertAll(
                () -> assertEquals(copyOfBook.getId(), copyOfBookDto.getId()),
                () -> assertEquals(copyOfBook.getBook().getId(), copyOfBookDto.getBookId()),
                () -> assertEquals(copyOfBook.getStatus(), copyOfBookDto.getStatus()),
                () -> assertEquals(copyOfBook.isRented(), copyOfBookDto.isRented())
        );
    }

    @Test
    void testMapToListCopyOfBook() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());

        List<CopyOfBookDto> copyOfBookDtoList = List.of(
                new CopyOfBookDto(1L, book.getId(), "Testowy", false),
                new CopyOfBookDto(2L, book.getId(), "Testowy1", false),
                new CopyOfBookDto(3L, book.getId(), "Testowy2", true)
        );

        List<CopyOfBook> copyOfBookList = copyOfBookMapper.mapToListCopyOfBook(copyOfBookDtoList);

        assertEquals(copyOfBookDtoList.size(), copyOfBookList.size());

        IntStream.range(0, Math.min(copyOfBookDtoList.size(), copyOfBookList.size()))
                .forEach(i -> {
                    assertEquals(copyOfBookDtoList.get(i).getId(), copyOfBookList.get(i).getId());
                    assertEquals(copyOfBookDtoList.get(i).getStatus(), copyOfBookList.get(i).getStatus());
                    assertEquals(copyOfBookDtoList.get(i).isRented(), copyOfBookList.get(i).isRented());
                });
    }

    @Test
    void testMapToListCopyOfBookDto() {
        Book book = new Book(1L, "Test", "Milosz", LocalDate.now(), new ArrayList<>());

        List<CopyOfBook> copyOfBookList = List.of(
                new CopyOfBook(1L, book, "Testowy", false, new ArrayList<>()),
                new CopyOfBook(2L, book, "Testowy1", false, new ArrayList<>()),
                new CopyOfBook(3L, book, "Testowy2", true, new ArrayList<>())
        );

        List<CopyOfBookDto> copyOfBookDtoList = copyOfBookMapper.mapToListCopyOfBookDto(copyOfBookList);

        assertEquals(copyOfBookList.size(), copyOfBookDtoList.size());

        IntStream.range(0, Math.min(copyOfBookList.size(), copyOfBookDtoList.size()))
                .forEach(i -> {
                    assertEquals(copyOfBookList.get(i).getId(), copyOfBookDtoList.get(i).getId());
                    assertEquals(copyOfBookList.get(i).getBook().getId(), copyOfBookDtoList.get(i).getBookId());
                    assertEquals(copyOfBookList.get(i).getStatus(), copyOfBookDtoList.get(i).getStatus());
                    assertEquals(copyOfBookList.get(i).isRented(), copyOfBookDtoList.get(i).isRented());
                });
    }

}