package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import com.example.library.mapper.CopyOfBookMapper;
import com.example.library.service.BookService;
import com.example.library.service.CopyOfBookService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CopyOfBookController.class)
class CopyOfBookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CopyOfBookMapper copyOfBookMapper;
    @MockBean
    private CopyOfBookService copyOfBookService;
    @MockBean
    private BookService bookService;

    @Test
    void createNewCopyOfBook() throws Exception {
        Book book = new Book(1L, "Milosz", "Test CopyOfBookController", LocalDate.now(), new ArrayList<>());

        CopyOfBook copyOfBook = new CopyOfBook(1L, book, "in good shape", false, new ArrayList<>());
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, book.getId(), "in good shape", false);

        when(copyOfBookMapper.mapToCopyOfBook(any(CopyOfBookDto.class))).thenReturn(copyOfBook);
        when(bookService.findById(copyOfBookDto.getId())).thenReturn(Optional.of(book));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(copyOfBookDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/v1/copyOfBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void changeCopyOfBookStatus() throws Exception {
        Book book = new Book(1L, "Milosz", "Test CopyOfBookController", LocalDate.now(), new ArrayList<>());

        Optional<CopyOfBook> optionalCopyOfBook = Optional.of(
                new CopyOfBook(1L, book, "in good shape", false, new ArrayList<>())
        );
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, book.getId(), "BrandNew", false);

        when(copyOfBookService.findById(copyOfBookDto.getId())).thenReturn(optionalCopyOfBook);
        when(copyOfBookService.saveCopyOfBook(any(CopyOfBook.class))).thenReturn(optionalCopyOfBook.get());
        when(copyOfBookMapper.mapToCopyOfBookDto(any(CopyOfBook.class))).thenReturn(copyOfBookDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .put("/v1/copyOfBook/1/BrandNew")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("BrandNew")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rented", Matchers.is(false)));
    }

    @Test
    void howManyCopiesAreAvailable() throws Exception {

        Book book = new Book(1L, "Milosz", "Test CopyOfBookController", LocalDate.now(), new ArrayList<>());

        List<CopyOfBook> copyOfBookList = List.of(
                new CopyOfBook(1L, book, "BrandNew", true, new ArrayList<>()),
                new CopyOfBook(2L, book, "Fine", true, new ArrayList<>())
        );
        List<CopyOfBookDto> copyOfBookDtoList = List.of(
                new CopyOfBookDto(1L, book.getId(), "BrandNew", true),
                new CopyOfBookDto(2L, book.getId(), "Fine", true)
        );
        when(copyOfBookService.findAvailableCopyOfBook(book.getTitle())).thenReturn(copyOfBookList);
        when(copyOfBookMapper.mapToListCopyOfBookDto(copyOfBookList)).thenReturn(copyOfBookDtoList);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .get("/v1/copyOfBook/Milosz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is("BrandNew")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status", Matchers.is("Fine")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rented", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rented", Matchers.is(true)));


    }
}