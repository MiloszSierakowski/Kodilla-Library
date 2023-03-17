package com.example.library.controller;

import com.example.library.domain.*;
import com.example.library.mapper.LocalDateAdapter;
import com.example.library.mapper.RentalMapper;
import com.example.library.service.CopyOfBookService;
import com.example.library.service.ReaderService;
import com.example.library.service.RentalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RentalController.class)
class RentalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RentalMapper rentalMapper;
    @MockBean
    private RentalService rentalService;
    @MockBean
    private CopyOfBookService copyOfBookService;
    @MockBean
    private ReaderService readerService;

    @Test
    void createNewRental() throws Exception {
        CopyOfBook copyOfBook = new CopyOfBook(2L, new Book(), "Fine", true, new ArrayList<>());
        Reader reader = new Reader(3L, "Milosz", "S", LocalDate.now(), new ArrayList<>());

        Rental rental = new Rental(1L, copyOfBook, reader, LocalDate.now(), LocalDate.now());
        RentalDto rentalDto = new RentalDto(1L, copyOfBook.getId(), reader.getId(), LocalDate.now(), LocalDate.now());

        when(rentalMapper.mapToRental(any(RentalDto.class))).thenReturn(rental);
        when(copyOfBookService.findById(rentalDto.getCopyOfBookId())).thenReturn(Optional.of(copyOfBook));
        when(readerService.findById(rentalDto.getReaderId())).thenReturn(Optional.of(reader));

        when(rentalService.saveNewRental(any(Rental.class))).thenReturn(rental);
        when(copyOfBookService.saveCopyOfBook(any(CopyOfBook.class))).thenReturn(copyOfBook);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String jsonContent = gson.toJson(rentalDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/v1/rental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void finishRental() throws Exception {
        CopyOfBook copyOfBook = new CopyOfBook(2L, new Book(), "Fine", true, new ArrayList<>());
        Reader reader = new Reader(3L, "Milosz", "S", LocalDate.now(), new ArrayList<>());

        Rental rental = new Rental(1L, copyOfBook, reader, LocalDate.now(), null);
        RentalDto rentalDto = new RentalDto(1L, copyOfBook.getId(), reader.getId(), LocalDate.now(), LocalDate.now());

        when(rentalService.findById(rentalDto.getId())).thenReturn(Optional.of(rental));
        when(rentalMapper.mapToRentalDto(any(Rental.class))).thenReturn(rentalDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String jsonContent = gson.toJson(rentalDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .put("/v1/rental")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.copyOfBookId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.readerId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentDate", Matchers.is("2023-03-16")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate", Matchers.is("2023-03-16")));
    }
}