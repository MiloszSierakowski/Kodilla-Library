package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.LocalDateAdapter;
import com.example.library.service.BookService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookMapper bookMapper;
    @MockBean
    private BookService bookService;

    @Test
    void createNewBook() throws Exception {
        Book book = new Book(1L, "Test book", "MiloszS", LocalDate.now(), new ArrayList<>());
        BookDto bookDto = new BookDto(1L, "Test book", "MiloszS", LocalDate.now());

        when(bookMapper.mapToBook(any(BookDto.class))).thenReturn(book);
        when(bookService.saveBook(any(Book.class))).thenReturn(book);


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        String jsonContent = gson.toJson(bookDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}